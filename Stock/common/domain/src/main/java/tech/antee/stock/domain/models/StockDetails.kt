package tech.antee.stock.domain.models

data class StockDetails(
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
    val inSubList: Boolean
)
