package tech.antee.stock.stock_details.impl.ui.mappers

import tech.antee.stock.domain.models.StockDetails
import tech.antee.stock.stock_details.impl.ui.models.StockDetailsItem
import javax.inject.Inject

class StockDetailsUiMapper @Inject constructor() {

    fun mapFromDomain(model: StockDetails): StockDetailsItem = with(model) {
        StockDetailsItem(
            id,
            name,
            ticker,
            price,
            priceChange,
            percentChange,
            high24h,
            low24h,
            lastUpdated,
            chartData,
            imageUrl,
            inSubList
        )
    }
}
