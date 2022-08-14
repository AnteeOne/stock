package tech.antee.stock.stock_list.di

import dagger.Component
import tech.antee.stock.stock_list.ui.StockListViewModel

@Component(
    modules = [StockListModule::class],
    dependencies = [StockListDependencies::class]
)
interface StockListComponent {

    val viewModel: StockListViewModel

    @Component.Factory
    interface Factory {
        fun create(deps: StockListDependencies): StockListComponent
    }
}
