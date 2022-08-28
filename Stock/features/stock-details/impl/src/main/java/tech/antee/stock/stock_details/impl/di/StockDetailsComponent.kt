package tech.antee.stock.stock_details.impl.di

import dagger.BindsInstance
import dagger.Component
import tech.antee.stock.di.qualifiers.StockId
import tech.antee.stock.stock_details.impl.ui.StockDetailsViewModel

@Component(
    modules = [StockDetailsModule::class],
    dependencies = [StockDetailsDependencies::class]
)
interface StockDetailsComponent {

    val viewModel: StockDetailsViewModel

    @Component.Factory
    interface Factory {
        fun create(
            deps: StockDetailsDependencies,
            @StockId
            @BindsInstance
            stockId: String,
        ): StockDetailsComponent
    }
}
