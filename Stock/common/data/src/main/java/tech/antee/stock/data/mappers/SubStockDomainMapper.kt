package tech.antee.stock.data.mappers

import tech.antee.stock.data.local.entities.SubStockEntity
import tech.antee.stock.domain.models.SubStock
import javax.inject.Inject

class SubStockDomainMapper @Inject constructor() {

    fun mapFromData(entity: SubStockEntity): SubStock = with(entity) {
        SubStock(stockId, price)
    }
}
