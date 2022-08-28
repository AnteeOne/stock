package tech.antee.stock.domain.repositories

import tech.antee.stock.domain.models.StockDetails
import tech.antee.stock.domain.models.StockInList

interface StockRepository {

    suspend fun getStocks(): List<StockInList>

    suspend fun getStockDetails(id: String): StockDetails
}
