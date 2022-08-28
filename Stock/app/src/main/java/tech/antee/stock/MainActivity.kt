package tech.antee.stock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import tech.antee.stock.di.LocalAppProvider
import tech.antee.stock.multi_compose.find
import tech.antee.stock.stock_details.impl.di.LocalStockDetailsDependencies
import tech.antee.stock.stock_list.api.StockListFeature
import tech.antee.stock.stock_list.di.LocalStockListDependencies
import tech.antee.stock.ui.theme.StockTheme
import tech.antee.stock_details.StockDetailsFeature

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockTheme {
                GlobalDependenciesProvider {
                    Navigation()
                }
                TransparentSystemBarEffect()
            }
        }
    }

    @Composable
    private fun GlobalDependenciesProvider(
        content: @Composable () -> Unit
    ) {
        CompositionLocalProvider(
            LocalAppProvider provides application.appProvider,
            LocalStockListDependencies provides application.appProvider,
            LocalStockDetailsDependencies provides application.appProvider,
            content = content
        )
    }

    @Composable
    private fun TransparentSystemBarEffect() {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = true
            )
        }
    }

    @Composable
    private fun Navigation() {
        val navController = rememberNavController()
        val destinations = LocalAppProvider.current.destinations

        val stockListFeature = destinations.find<StockListFeature>()
        val stockDetailFeature = destinations.find<StockDetailsFeature>()

        Box(Modifier.fillMaxSize()) {
            NavHost(navController, stockListFeature.featureRoute) {
                with(stockListFeature) { composable(navController, destinations) }
                with(stockDetailFeature) { composable(navController, destinations) }
            }
        }
    }
}
