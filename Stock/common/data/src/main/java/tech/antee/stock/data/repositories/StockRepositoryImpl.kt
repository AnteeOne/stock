package tech.antee.stock.data.repositories

import tech.antee.stock.data.mappers.StockDomainMapper
import tech.antee.stock.data.sources.RemoteStockSource
import tech.antee.stock.domain.models.StockInList
import tech.antee.stock.domain.repositories.StockRepository
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val remoteStockSource: RemoteStockSource,
    private val stockDomainMapper: StockDomainMapper
) : StockRepository {

    override suspend fun getStocks(): List<StockInList> {
        return remoteStockSource.getStockList().map(stockDomainMapper::mapFromDto)
    }
}
