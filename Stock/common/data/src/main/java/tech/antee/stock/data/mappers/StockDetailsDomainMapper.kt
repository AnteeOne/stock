package tech.antee.stock.data.mappers

import tech.antee.stock.data.local.entities.SubStockEntity
import tech.antee.stock.data.remote.dto.StockChartDto
import tech.antee.stock.data.remote.dto.StockDetailsDto
import tech.antee.stock.domain.models.ChartPoint
import tech.antee.stock.domain.models.StockDetails
import javax.inject.Inject

class StockDetailsDomainMapper @Inject constructor() {

    fun mapFromData(
        stockDetailsDto: StockDetailsDto,
        stockChartDto: StockChartDto,
        subStockEntities: List<SubStockEntity>
    ): StockDetails = with(stockDetailsDto) {
        StockDetails(
            id = id,
            name = name,
            ticker = symbol,
            price = currentPrice,
            priceChange = priceChange24h,
            percentChange = marketCapChangePercentage24h,
            high24h = high24h,
            low24h = low24h,
            lastUpdated = lastUpdated,
            chartData = stockChartDto.prices.map { ChartPoint(it[0].toLong(), it[1]) },
            imageUrl = image,
            inSubList = subStockEntities.any { it.stockId == id }
        )
    }
}
