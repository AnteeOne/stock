package tech.antee.stock.stock_robot.impl.di

import tech.antee.stock.di.Dependencies
import tech.antee.stock.domain.repositories.RobotRepository
import tech.antee.stock.domain.usecases.CheckStockSubUsecase
import tech.antee.stock.domain.usecases.GetSubStocksUsecase

interface StockRobotDependencies : Dependencies {

    val getSubStocksUsecase: GetSubStocksUsecase

    val checkStockSubUsecase: CheckStockSubUsecase

    val robotRepository: RobotRepository
}
