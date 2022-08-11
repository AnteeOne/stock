package tech.antee.stock.stock_list.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import tech.antee.stock.multi_compose.Destinations
import tech.antee.stock.stock_list.api.StockListFeature

class StockListFeatureImpl : StockListFeature() {

    @Composable
    override fun NavGraphBuilder.Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        // TODO: add implementation
    }
}
