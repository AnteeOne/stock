package tech.antee.stock.data.local.dao

import androidx.room.*
import tech.antee.stock.data.local.entities.SubResultEntity
import tech.antee.stock.data.local.entities.SubStockEntity

@Dao
interface StockDao {

    @Query("SELECT * FROM sub_stock_entities")
    fun getAllSubStocks(): List<SubStockEntity>

    @Query("SELECT * FROM sub_result_entities")
    fun getAllSubResults(): List<SubResultEntity>

    @Query("SELECT * FROM sub_stock_entities WHERE stockId LIKE :id LIMIT 1")
    fun getSubStockById(id: String): SubStockEntity

    @Query("SELECT * FROM sub_result_entities WHERE stockId LIKE :id LIMIT 1")
    fun getSubResultById(id: String): SubResultEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubStocks(vararg subStocks: SubStockEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubResults(vararg subResults: SubResultEntity)

    @Delete
    fun deleteSubStocks(vararg subStocks: SubStockEntity)

    @Delete
    fun deleteSubResults(vararg subResults: SubResultEntity)
}
