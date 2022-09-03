package tech.antee.stock.domain.usecases

import tech.antee.stock.domain.models.StockInList
import tech.antee.stock.domain.repositories.StockRepository
import javax.inject.Inject

class GetStockListUsecase @Inject constructor(
    private val stockRepository: StockRepository
) {
    suspend operator fun invoke(): List<StockInList> = stockRepository.getStocks()

    suspend operator fun invoke(
        perPage: Int,
        page: Int
    ): List<StockInList> = stockRepository.getStocks(perPage, page)
}
