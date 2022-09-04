package tech.antee.stock.stock_list.di

import androidx.compose.runtime.compositionLocalOf
import tech.antee.stock.domain.repositories.RobotRepository
import tech.antee.stock.domain.repositories.StockRepository
import tech.antee.stock.stock_robot.api.StockRobot

interface StockListDependencies {

    val stockRepository: StockRepository

    val stockRobot: StockRobot

    val robotRepository: RobotRepository
}

val LocalStockListDependencies = compositionLocalOf<StockListDependencies> {
    error("No feature deps provider found!")
}
