package tech.antee.stock.di

import androidx.compose.runtime.compositionLocalOf
import tech.antee.stock.multi_compose.Destinations
import tech.antee.stock.stock_list.di.StockListDependencies

interface AppProvider : StockListDependencies {

    val destinations: Destinations
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
