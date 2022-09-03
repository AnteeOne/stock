package tech.antee.stock.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.antee.stock.data.local.dao.StockDao
import tech.antee.stock.data.local.entities.SubResultEntity
import tech.antee.stock.data.local.entities.SubStockEntity

@Database(
    entities = [SubResultEntity::class, SubStockEntity::class],
    version = 1
)
abstract class StockDatabase : RoomDatabase() {
    abstract fun stockDao(): StockDao
}
