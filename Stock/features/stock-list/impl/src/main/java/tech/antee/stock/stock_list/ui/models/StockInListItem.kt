package tech.antee.stock.stock_list.ui.models

data class StockInListItem(
    val id: String,
    val name: String,
    val ticker: String,
    val price: Double,
    val priceChange: Double,
    val percentChange: Double,
    val imageUrl: String
)
