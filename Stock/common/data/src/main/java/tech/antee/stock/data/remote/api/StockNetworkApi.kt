package tech.antee.stock.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import tech.antee.stock.data.remote.dto.StockInListDto

interface StockNetworkApi {

    @GET("coins/markets")
    suspend fun getStocks(
        @Query("vs_currency") currency: String = "usd",
        @Query("per_page") perPage: Int? = null,
        @Query("") page: Int? = null
    ): List<StockInListDto>
}
