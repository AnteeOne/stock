package tech.antee.stock.data.mappers

import tech.antee.stock.data.remote.dto.StockInListDto
import tech.antee.stock.domain.models.StockInList
import javax.inject.Inject

class StockDomainMapper @Inject constructor() {

    fun mapFromDto(dto: StockInListDto): StockInList = with(dto) {
        StockInList(
            id,
            name,
            symbol,
            currentPrice,
            priceChange24h,
            priceChangePercentage24h
        )
    }
}
