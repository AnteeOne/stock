package tech.antee.stock.domain.usecases

import tech.antee.stock.domain.models.SubStock
import tech.antee.stock.domain.repositories.StockRepository
import javax.inject.Inject

class GetSubStocksUsecase @Inject constructor(
    private val stockRepository: StockRepository
) {

    suspend operator fun invoke(): List<SubStock> = stockRepository.getSubStocks()
}
