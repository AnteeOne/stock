package tech.antee.stock.stock_list.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import tech.antee.stock.di.injectedViewModel
import tech.antee.stock.multi_compose.Destinations
import tech.antee.stock.multi_compose.find
import tech.antee.stock.stock_list.api.StockListFeature
import tech.antee.stock.stock_list.di.DaggerStockListComponent
import tech.antee.stock.stock_list.di.LocalStockListDependencies
import tech.antee.stock.stock_robot.api.StockRobot
import tech.antee.stock_details.StockDetailsFeature
import javax.inject.Inject

class StockListFeatureImpl @Inject constructor() : StockListFeature() {

    @Composable
    override fun NavGraphBuilder.Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val deps = LocalStockListDependencies.current
        val viewModel = injectedViewModel {
            DaggerStockListComponent.factory()
                .create(deps)
                .viewModel
        }

        val stockDetailsFeature = destinations.find<StockDetailsFeature>()
        StockListScreen(
            viewModel = viewModel,
            onStockClick = { stockId ->
                navController.navigate(stockDetailsFeature.destination(stockId))
            }
        )
    }
}
