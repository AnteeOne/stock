package tech.antee.stock.data.sources

import tech.antee.stock.data.remote.dto.StockInListDto

interface RemoteStockSource {

    suspend fun getStockList(): List<StockInListDto>
}
