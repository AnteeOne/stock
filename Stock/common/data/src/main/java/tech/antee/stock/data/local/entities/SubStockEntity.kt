package tech.antee.stock.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sub_stock_entities")
data class SubStockEntity(
    @PrimaryKey val stockId: String,
    @ColumnInfo(name = "price") val price: Double
)
