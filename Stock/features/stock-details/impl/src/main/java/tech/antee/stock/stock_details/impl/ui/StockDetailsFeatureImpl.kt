package tech.antee.stock.stock_details.impl.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import tech.antee.stock.di.injectedViewModel
import tech.antee.stock.multi_compose.Destinations
import tech.antee.stock.stock_details.impl.di.DaggerStockDetailsComponent
import tech.antee.stock.stock_details.impl.di.LocalStockDetailsDependencies
import tech.antee.stock_details.StockDetailsFeature
import javax.inject.Inject

class StockDetailsFeatureImpl @Inject constructor() : StockDetailsFeature() {

    @Composable
    override fun NavGraphBuilder.Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        backStackEntry.arguments?.getString(stockIdArgument)?.let { stockId ->
            val deps = LocalStockDetailsDependencies.current
            val viewModel = injectedViewModel {
                DaggerStockDetailsComponent.factory()
                    .create(deps, stockId)
                    .viewModel
            }
            StockDetailsScreen(
                viewModel = viewModel,
                onBackButtonClick = { navController.popBackStack() }
            )
        }
    }
}
