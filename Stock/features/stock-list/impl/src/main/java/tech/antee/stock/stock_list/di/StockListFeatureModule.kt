package tech.antee.stock.stock_list.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tech.antee.stock.di.FeatureKey
import tech.antee.stock.multi_compose.Feature
import tech.antee.stock.stock_list.api.StockListFeature
import tech.antee.stock.stock_list.ui.StockListFeatureImpl
import javax.inject.Singleton

@Module
interface StockListFeatureModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureKey(StockListFeature::class)
    fun stockListFeature(feature: StockListFeatureImpl): Feature
}
