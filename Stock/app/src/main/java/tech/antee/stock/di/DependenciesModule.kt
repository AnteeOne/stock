package tech.antee.stock.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tech.antee.stock.di.qualifiers.DependenciesKey
import tech.antee.stock.stock_robot.impl.di.StockRobotDependencies

@Module
interface DependenciesModule {

    @Binds
    @IntoMap
    @DependenciesKey(StockRobotDependencies::class)
    fun stockRobotDependencies(impl: AppComponent): Dependencies
}
