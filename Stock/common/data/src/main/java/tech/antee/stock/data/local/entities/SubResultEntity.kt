package tech.antee.stock.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sub_result_entities")
data class SubResultEntity(
    @PrimaryKey val stockId: String,
    @ColumnInfo(name = "initial_price") val initialPrice: Double,
    @ColumnInfo(name = "final_price") val finalPrice: Double
)
