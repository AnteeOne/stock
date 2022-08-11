package tech.antee.stock.stock_list.ui.models

data class StockListModel(
    val id: Long,
    val name: String,
    val ticker: String,
    val price: Double,
    val priceChange: Double,
    val percentChange: Double
)