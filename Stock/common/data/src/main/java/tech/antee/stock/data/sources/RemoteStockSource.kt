package tech.antee.stock.data.sources

import tech.antee.stock.data.remote.dto.StockChartDto
import tech.antee.stock.data.remote.dto.StockDetailsDto
import tech.antee.stock.data.remote.dto.StockInListDto

interface RemoteStockSource {

    suspend fun getStockList(): List<StockInListDto>

    suspend fun getStockDetails(stockId: String): StockDetailsDto

    suspend fun getStockChart(stockId: String): StockChartDto
}
