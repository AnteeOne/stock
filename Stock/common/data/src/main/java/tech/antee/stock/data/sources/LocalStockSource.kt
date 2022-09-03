package tech.antee.stock.data.sources

import tech.antee.stock.data.local.entities.SubResultEntity
import tech.antee.stock.data.local.entities.SubStockEntity

interface LocalStockSource {

    fun getAllSubStocks(): List<SubStockEntity>

    fun getAllSubResults(): List<SubResultEntity>

    fun getSubStockById(id: String): SubStockEntity

    fun getSubResultById(id: String): SubResultEntity

    fun insertSubStocks(vararg subStocks: SubStockEntity)

    fun insertSubResults(vararg subResults: SubResultEntity)

    fun deleteSubStocks(vararg subStocks: SubStockEntity)

    fun deleteSubResults(vararg subResults: SubResultEntity)
}
