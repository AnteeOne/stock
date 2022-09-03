package tech.antee.stock.stock_details.impl.di

import androidx.compose.runtime.compositionLocalOf
import tech.antee.stock.domain.usecases.GetStockDetailsUsecase
import tech.antee.stock.domain.usecases.HandleSubUsecase

interface StockDetailsDependencies {

    val getStockDetailsUsecase: GetStockDetailsUsecase

    val handleSubUsecase: HandleSubUsecase
}

val LocalStockDetailsDependencies = compositionLocalOf<StockDetailsDependencies> {
    error("No feature deps provider found!")
}
