package tech.antee.stock.di

import dagger.Module
import tech.antee.stock.data.di.DataModule
import tech.antee.stock.stock_robot.impl.di.StockRobotModule

@Module(
    includes = [DataModule::class, StockRobotModule::class]
)
interface AppModule {



}
