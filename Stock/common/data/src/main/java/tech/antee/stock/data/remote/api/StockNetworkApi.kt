package tech.antee.stock.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tech.antee.stock.data.remote.dto.StockChartDto
import tech.antee.stock.data.remote.dto.StockDetailsDto
import tech.antee.stock.data.remote.dto.StockInListDto

interface StockNetworkApi {

    @GET("coins/markets")
    suspend fun getStocksInList(
        @Query("vs_currency") currency: String = "usd",
        @Query("per_page") perPage: Int? = null,
        @Query("") page: Int? = null
    ): List<StockInListDto>

    @GET("coins/markets")
    suspend fun getStocksDetails(
        @Query("ids") stockId: String,
        @Query("vs_currency") currency: String = "usd",
        @Query("per_page") perPage: Int? = null,
        @Query("") page: Int? = null
    ): List<StockDetailsDto>

    @GET("coins/{stock_id}/market_chart")
    suspend fun getStockChart(
        @Path("stock_id") stockId: String,
        @Query("vs_currency") currency: String = "usd",
        @Query("days") days: String = "max",
        @Query("interval") query: String = "daily",
    ): StockChartDto
}
