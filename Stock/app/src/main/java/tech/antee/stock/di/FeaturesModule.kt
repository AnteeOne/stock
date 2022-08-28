package tech.antee.stock.di

import dagger.Module
import tech.antee.stock.stock_details.impl.di.StockDetailsFeatureModule
import tech.antee.stock.stock_list.di.StockListFeatureModule

@Module(
    includes = [
        StockListFeatureModule::class,
        StockDetailsFeatureModule::class
    ]
)
interface FeaturesModule
