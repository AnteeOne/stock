package tech.antee.stock.data.sources

import tech.antee.stock.data.remote.api.StockNetworkApi
import tech.antee.stock.data.remote.dto.StockInListDto
import javax.inject.Inject

class RemoteStockSourceImpl @Inject constructor(
    private val api: StockNetworkApi
) : RemoteStockSource {

    override suspend fun getStockList(): List<StockInListDto> = api.getStocks()
}
