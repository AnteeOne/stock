package tech.antee.stock.data.di

import dagger.Binds
import dagger.Module
import tech.antee.stock.data.sources.RemoteStockSource
import tech.antee.stock.data.sources.RemoteStockSourceImpl
import javax.inject.Singleton

@Module
interface SourcesModule {

    @Binds
    @Singleton
    fun remoteStockSource(remoteStockSourceImpl: RemoteStockSourceImpl): RemoteStockSource
}
