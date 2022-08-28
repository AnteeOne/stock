package tech.antee.stock.domain.usecases

import tech.antee.stock.domain.models.StockDetails
import tech.antee.stock.domain.repositories.StockRepository
import javax.inject.Inject

class GetStockDetailsUsecase @Inject constructor(
    private val stockRepository: StockRepository
) {
    suspend operator fun invoke(stockId: String): StockDetails = stockRepository.getStockDetails(stockId)
}
