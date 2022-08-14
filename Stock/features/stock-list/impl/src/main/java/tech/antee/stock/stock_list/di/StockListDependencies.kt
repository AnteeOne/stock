package tech.antee.stock.stock_list.di

import androidx.compose.runtime.compositionLocalOf
import tech.antee.stock.domain.repositories.StockRepository

interface StockListDependencies {

    val stockRepository: StockRepository
}

val LocalStockListDependencies = compositionLocalOf<StockListDependencies> {
    error("No feature deps provider found!")
}
