package tech.antee.stock.domain.repositories

import kotlinx.coroutines.flow.Flow
import tech.antee.stock.domain.models.StockDetails
import tech.antee.stock.domain.models.StockInList

interface StockRepository {

    suspend fun getStocks(): List<StockInList>

    suspend fun getStockDetails(id: String): StockDetails

    suspend fun getStocks(perPage: Int, page: Int): List<StockInList>

    suspend fun getStockDetailsFlow(id: String): Flow<StockDetails>

    suspend fun subscribeToStock(stockId: String, actualPrice: Double)

    suspend fun unsubscribeFromStock(stockId: String)
}
