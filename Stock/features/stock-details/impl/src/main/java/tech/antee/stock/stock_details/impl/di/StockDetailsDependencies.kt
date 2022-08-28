package tech.antee.stock.stock_details.impl.di

import androidx.compose.runtime.compositionLocalOf
import tech.antee.stock.domain.usecases.GetStockDetailsUsecase

interface StockDetailsDependencies {

    val getStockDetailsUsecase: GetStockDetailsUsecase
}

val LocalStockDetailsDependencies = compositionLocalOf<StockDetailsDependencies> {
    error("No feature deps provider found!")
}
