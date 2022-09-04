package tech.antee.stock.di

import androidx.compose.runtime.compositionLocalOf
import tech.antee.stock.multi_compose.Destinations
import tech.antee.stock.stock_details.impl.di.StockDetailsDependencies
import tech.antee.stock.stock_list.di.StockListDependencies
import tech.antee.stock.stock_robot.impl.di.StockRobotDependencies

interface AppProvider :
    StockListDependencies,
    StockDetailsDependencies,
    StockRobotDependencies {

    val destinations: Destinations

    val dependencies: DependenciesMap
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
