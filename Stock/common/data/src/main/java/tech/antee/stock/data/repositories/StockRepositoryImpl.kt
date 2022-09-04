package tech.antee.stock.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import tech.antee.stock.data.local.entities.SubStockEntity
import tech.antee.stock.data.mappers.StockDetailsDomainMapper
import tech.antee.stock.data.mappers.StockInListDomainMapper
import tech.antee.stock.data.mappers.StockResultDomainMapper
import tech.antee.stock.data.mappers.SubStockDomainMapper
import tech.antee.stock.data.sources.LocalStockSource
import tech.antee.stock.data.sources.RemoteStockSource
import tech.antee.stock.domain.models.StockDetails
import tech.antee.stock.domain.models.StockInList
import tech.antee.stock.domain.models.SubResult
import tech.antee.stock.domain.models.SubStock
import tech.antee.stock.domain.repositories.StockRepository
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val remoteStockSource: RemoteStockSource,
    private val localStockSource: LocalStockSource,
    private val stockInListDomainMapper: StockInListDomainMapper,
    private val stockDetailsDomainMapper: StockDetailsDomainMapper,
    private val subStockDomainMapper: SubStockDomainMapper,
    private val stockResultDomainMapper: StockResultDomainMapper
) : StockRepository {

    override suspend fun getStocks(): List<StockInList> {
        return remoteStockSource.getStockList().map(stockInListDomainMapper::mapFromDto)
    }

    override suspend fun getStocks(
        perPage: Int,
        page: Int
    ): List<StockInList> {
        return remoteStockSource.getStockList(perPage, page).map(stockInListDomainMapper::mapFromDto)
    }

    override suspend fun getStockDetails(id: String): StockDetails = coroutineScope {
        with(remoteStockSource) {
            val stockDetails = async { getStockDetails(id) }
            val stockChart = async { getStockChart(id) }
            val subStocks = async(Dispatchers.IO) { localStockSource.getAllSubStocks() }
            stockDetailsDomainMapper.mapFromData(stockDetails.await(), stockChart.await(), subStocks.await())
        }
    }

    override suspend fun getCurrentStockPrice(id: String): Double {
        with(remoteStockSource) {
            val stockDetails = getStockDetails(id)
            return stockDetails.currentPrice
        }
    }

    override suspend fun getStockDetailsFlow(id: String): Flow<StockDetails> = flow {
        coroutineScope {
            with(remoteStockSource) {
                val stockDetails = async { getStockDetails(id) }
                val stockChart = async { getStockChart(id) }
                emit(stockDetails.await() to stockChart.await())
            }
        }
    }.combine(localStockSource.getSubStockFlowById(id)) { stockDto, subStock ->
        stockDetailsDomainMapper.mapFromData(
            stockDto.first, stockDto.second, listOfNotNull(subStock)
        )
    }

    override suspend fun getSubStocks(): List<SubStock> =
        localStockSource.getAllSubStocks().map(subStockDomainMapper::mapFromData)

    override suspend fun getSubResultsFlow(): Flow<List<SubResult>> =
        localStockSource.getAllSubResultsFlow().map { it.map(stockResultDomainMapper::mapFromData) }

    override suspend fun addSubResult(subResult: SubResult) {
        localStockSource.insertSubResults(stockResultDomainMapper.mapFromDomain(subResult))
    }

    override suspend fun subscribeToStock(
        stockId: String,
        actualPrice: Double
    ): Unit = with(localStockSource) {
        insertSubStocks(
            SubStockEntity(
                stockId = stockId,
                price = actualPrice
            )
        )
    }

    override suspend fun unsubscribeFromStock(stockId: String): Unit = with(localStockSource) {
        deleteSubStocks(
            SubStockEntity(
                stockId = stockId,
                price = 0.toDouble()
            )
        )
    }
}
