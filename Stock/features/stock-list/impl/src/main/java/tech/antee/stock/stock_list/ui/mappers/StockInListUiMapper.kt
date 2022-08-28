package tech.antee.stock.stock_list.ui.mappers

import tech.antee.stock.domain.models.StockInList
import tech.antee.stock.stock_list.ui.models.StockInListItem
import javax.inject.Inject

class StockInListUiMapper @Inject constructor() {

    fun mapFromDomain(model: StockInList): StockInListItem = with(model) {
        StockInListItem(id, name, ticker, price, priceChange, percentChange, imageUrl)
    }
}
