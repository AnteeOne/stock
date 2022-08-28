package tech.antee.stock_details

import androidx.navigation.NavType
import androidx.navigation.navArgument
import tech.antee.stock.multi_compose.ComposableFeature

abstract class StockDetailsFeature : ComposableFeature {

    protected val stockIdArgument = "stockId"

    final override val featureRoute = "stockDetails?$stockIdArgument={stockId}"

    final override val arguments = listOf(
        navArgument(stockIdArgument) {
            type = NavType.StringType
        }
    )

    private fun buildRoute(id: String) = "stockDetails?$stockIdArgument=$id"

    fun destination(id: String) = buildRoute(id)
}
