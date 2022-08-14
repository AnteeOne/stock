package tech.antee.stock.data.di

import dagger.Binds
import dagger.Module
import tech.antee.stock.data.remote.di.NetworkModule
import tech.antee.stock.data.repositories.StockRepositoryImpl
import tech.antee.stock.domain.repositories.StockRepository
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, SourcesModule::class])
interface DataModule {

    @Binds
    @Singleton
    fun stockRepository(impl: StockRepositoryImpl): StockRepository
}
