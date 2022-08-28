package tech.antee.stock.stock_details.impl.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tech.antee.stock.di.FeatureKey
import tech.antee.stock.multi_compose.Feature
import tech.antee.stock.stock_details.impl.ui.StockDetailsFeatureImpl
import tech.antee.stock_details.StockDetailsFeature
import javax.inject.Singleton

@Module
interface StockDetailsFeatureModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureKey(StockDetailsFeature::class)
    fun stockDetailsFeature(feature: StockDetailsFeatureImpl): Feature
}
