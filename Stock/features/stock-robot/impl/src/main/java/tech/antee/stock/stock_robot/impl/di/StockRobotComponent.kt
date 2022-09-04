package tech.antee.stock.stock_robot.impl.di

import dagger.Component
import tech.antee.stock.di.scopes.FeatureScope
import tech.antee.stock.stock_robot.impl.StockRobotService

@FeatureScope
@Component(
    dependencies = [StockRobotDependencies::class]
)
interface StockRobotComponent {

    fun inject(service: StockRobotService)

    @Component.Factory
    interface Factory {
        fun create(deps: StockRobotDependencies): StockRobotComponent
    }
}
