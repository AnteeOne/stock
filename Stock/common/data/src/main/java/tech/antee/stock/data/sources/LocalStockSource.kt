package tech.antee.stock.data.sources

import kotlinx.coroutines.flow.Flow
import tech.antee.stock.data.local.entities.SubResultEntity
import tech.antee.stock.data.local.entities.SubStockEntity

interface LocalStockSource {

    suspend fun getAllSubStocks(): List<SubStockEntity>

    suspend fun getAllSubResults(): List<SubResultEntity>

    suspend fun getSubStockById(id: String): SubStockEntity

    fun getSubStockFlowById(id: String): Flow<SubStockEntity?>

    suspend fun getSubResultById(id: String): SubResultEntity

    suspend fun insertSubStocks(vararg subStocks: SubStockEntity)

    suspend fun insertSubResults(vararg subResults: SubResultEntity)

    suspend fun deleteSubStocks(vararg subStocks: SubStockEntity)

    suspend fun deleteSubResults(vararg subResults: SubResultEntity)
}
