package tech.antee.stock.stock_robot.impl.di

import dagger.Binds
import dagger.Module
import tech.antee.stock.stock_robot.api.StockRobot
import tech.antee.stock.stock_robot.impl.StockRobotImpl

@Module
interface StockRobotModule {

    @Binds
    fun stockRobot(impl: StockRobotImpl): StockRobot
}
