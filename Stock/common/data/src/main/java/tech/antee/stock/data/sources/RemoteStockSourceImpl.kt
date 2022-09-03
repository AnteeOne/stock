package tech.antee.stock.data.sources

import tech.antee.stock.data.remote.api.StockNetworkApi
import tech.antee.stock.data.remote.dto.StockChartDto
import tech.antee.stock.data.remote.dto.StockDetailsDto
import tech.antee.stock.data.remote.dto.StockInListDto
import javax.inject.Inject

class RemoteStockSourceImpl @Inject constructor(
    private val api: StockNetworkApi
) : RemoteStockSource {

    override suspend fun getStockList(): List<StockInListDto> = api.getStocksInList()

    override suspend fun getStockList(
        perPage: Int,
        page: Int
    ): List<StockInListDto> = api.getStocksInList(perPage = perPage, page = page)

    override suspend fun getStockDetails(stockId: String): StockDetailsDto = api.getStocksDetails(stockId).first()

    override suspend fun getStockChart(stockId: String): StockChartDto = api.getStockChart(stockId)
}
