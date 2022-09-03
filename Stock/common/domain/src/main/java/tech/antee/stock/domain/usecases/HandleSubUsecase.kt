package tech.antee.stock.domain.usecases

import tech.antee.stock.domain.repositories.StockRepository
import javax.inject.Inject

class HandleSubUsecase @Inject constructor(
    private val stockRepository: StockRepository
) {

    suspend operator fun invoke(
        stockId: String,
        actualPrice: Double,
        isSubbed: Boolean
    ): Unit = with(stockRepository) {
        if (isSubbed) unsubscribeFromStock(stockId) else subscribeToStock(stockId, actualPrice)
    }
}
