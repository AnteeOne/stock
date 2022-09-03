package tech.antee.stock.data.repositories

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import tech.antee.stock.data.mappers.StockDetailsDomainMapper
import tech.antee.stock.data.mappers.StockInListDomainMapper
import tech.antee.stock.data.sources.RemoteStockSource
import tech.antee.stock.domain.models.StockDetails
import tech.antee.stock.domain.models.StockInList
import tech.antee.stock.domain.repositories.StockRepository
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val remoteStockSource: RemoteStockSource,
    private val stockInListDomainMapper: StockInListDomainMapper,
    private val stockDetailsDomainMapper: StockDetailsDomainMapper
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
            stockDetailsDomainMapper.mapFromDtos(stockDetails.await(), stockChart.await())
        }
    }
}
