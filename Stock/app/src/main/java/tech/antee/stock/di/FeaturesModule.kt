package tech.antee.stock.di

import dagger.Module
import tech.antee.stock.stock_list.di.StockListFeatureModule

@Module(
    includes = [StockListFeatureModule::class]
)
interface FeaturesModule
