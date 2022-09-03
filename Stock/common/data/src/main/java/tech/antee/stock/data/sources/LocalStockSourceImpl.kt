package tech.antee.stock.data.sources

import tech.antee.stock.data.local.dao.StockDao
import tech.antee.stock.data.local.entities.SubResultEntity
import tech.antee.stock.data.local.entities.SubStockEntity
import javax.inject.Inject

class LocalStockSourceImpl @Inject constructor(
    private val stockDao: StockDao
) : LocalStockSource {

    override fun getAllSubStocks(): List<SubStockEntity> = stockDao.getAllSubStocks()

    override fun getAllSubResults(): List<SubResultEntity> = stockDao.getAllSubResults()

    override fun getSubStockById(id: String): SubStockEntity = stockDao.getSubStockById(id)

    override fun getSubResultById(id: String): SubResultEntity = stockDao.getSubResultById(id)

    override fun insertSubStocks(vararg subStocks: SubStockEntity) = stockDao.insertSubStocks(*subStocks)

    override fun insertSubResults(vararg subResults: SubResultEntity) = stockDao.insertSubResults(*subResults)

    override fun deleteSubStocks(vararg subStocks: SubStockEntity) = stockDao.deleteSubStocks(*subStocks)

    override fun deleteSubResults(vararg subResults: SubResultEntity) = stockDao.deleteSubResults(*subResults)
}
