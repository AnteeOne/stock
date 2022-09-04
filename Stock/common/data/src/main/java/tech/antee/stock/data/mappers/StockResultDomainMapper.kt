package tech.antee.stock.data.mappers

import tech.antee.stock.data.local.entities.SubResultEntity
import tech.antee.stock.domain.models.SubResult
import javax.inject.Inject

class StockResultDomainMapper @Inject constructor() {

    fun mapFromData(entity: SubResultEntity): SubResult = with(entity) {
        SubResult(stockId, initialPrice, finalPrice)
    }

    fun mapFromDomain(model: SubResult): SubResultEntity = with(model) {
        SubResultEntity(stockId, initialPrice, finalPrice)
    }
}
