package tech.antee.stock.data.sources

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import tech.antee.stock.data.local.dao.StockDao
import tech.antee.stock.data.local.entities.SubResultEntity
import tech.antee.stock.data.local.entities.SubStockEntity
import javax.inject.Inject

class LocalStockSourceImpl @Inject constructor(
    private val stockDao: StockDao
) : LocalStockSource {

    override suspend fun getAllSubStocks(): List<SubStockEntity> =
        withContext(Dispatchers.IO) { stockDao.getAllSubStocks() }

    override suspend fun getAllSubResults(): List<SubResultEntity> =
        withContext(Dispatchers.IO) { stockDao.getAllSubResults() }

    override suspend fun getSubStockById(id: String): SubStockEntity =
        withContext(Dispatchers.IO) { stockDao.getSubStockById(id) }

    override fun getSubStockFlowById(id: String): Flow<SubStockEntity?> = stockDao.getSubStockFlowById(id)

    override suspend fun getSubResultById(id: String): SubResultEntity =
        withContext(Dispatchers.IO) { stockDao.getSubResultById(id) }

    override suspend fun insertSubStocks(vararg subStocks: SubStockEntity) =
        withContext(Dispatchers.IO) { stockDao.insertSubStocks(*subStocks) }

    override suspend fun insertSubResults(vararg subResults: SubResultEntity) = withContext(Dispatchers.IO) { stockDao.insertSubResults(*subResults) }

    override suspend fun deleteSubStocks(vararg subStocks: SubStockEntity) =
        withContext(Dispatchers.IO) { stockDao.deleteSubStocks(*subStocks) }

    override suspend fun deleteSubResults(vararg subResults: SubResultEntity) = withContext(Dispatchers.IO) { stockDao.deleteSubResults(*subResults) }
}
