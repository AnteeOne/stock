package tech.antee.stock.domain.usecases

import android.util.Log
import tech.antee.stock.domain.models.SubResult
import tech.antee.stock.domain.repositories.StockRepository
import javax.inject.Inject

class CheckStockSubUsecase @Inject constructor(
    private val stockRepository: StockRepository
) {
    suspend operator fun invoke(
        stockId: String,
        oldPrice: Double
    ): SubResult? {
        with(stockRepository) {
            Log.d(TAG, "Checking $stockId price...")
            val currentPrice = getCurrentStockPrice(stockId)
            Log.d(TAG, "Current $stockId price = $currentPrice")
            if (isCurrentPriceGood(oldPrice, currentPrice)) {
                Log.d(TAG, "$stockId price is GOOD for buying")
                val subResult = SubResult(
                    stockId = stockId,
                    initialPrice = oldPrice,
                    finalPrice = currentPrice
                )
                addSubResult(subResult)
                unsubscribeFromStock(stockId)
                return subResult
            }
            Log.d(TAG, "$stockId price is BAD for buying")
            return null
        }
    }

    private fun isCurrentPriceGood(
        oldPrice: Double,
        currentPrice: Double
    ): Boolean = currentPrice >= oldPrice * COEFFICIENT

    private companion object {
        const val TAG = "CheckStockSubUsecase"

        const val COEFFICIENT: Double = 1.05
    }
}
