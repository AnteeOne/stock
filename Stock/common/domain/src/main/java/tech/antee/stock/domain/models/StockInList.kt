package tech.antee.stock.domain.models

data class StockInList(
    val id: String,
    val name: String,
    val ticker: String,
    val price: Double,
    val priceChange: Double,
    val percentChange: Double
)
