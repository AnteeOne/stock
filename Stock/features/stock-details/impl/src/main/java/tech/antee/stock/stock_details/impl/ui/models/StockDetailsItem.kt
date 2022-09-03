package tech.antee.stock.stock_details.impl.ui.models

import tech.antee.stock.domain.models.ChartPoint

data class StockDetailsItem(
    val id: String,
    val name: String,
    val ticker: String,
    val price: Double,
    val priceChange: Double,
    val percentChange: Double,
    val high24h: Double,
    val low24h: Double,
    val lastUpdated: String,
    val chartData: List<ChartPoint>,
    val imageUrl: String,
    val isSubbed: Boolean
)
