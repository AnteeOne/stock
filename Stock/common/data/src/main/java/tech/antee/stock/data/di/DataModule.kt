package tech.antee.stock.data.di

import dagger.Binds
import dagger.Module
import tech.antee.stock.data.local.di.LocalModule
import tech.antee.stock.data.remote.di.NetworkModule
import tech.antee.stock.data.repositories.RobotRepositoryImpl
import tech.antee.stock.data.repositories.StockRepositoryImpl
import tech.antee.stock.domain.repositories.RobotRepository
import tech.antee.stock.domain.repositories.StockRepository
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        LocalModule::class,
        SourcesModule::class
    ]
)
interface DataModule {

    @Binds
    @Singleton
    fun stockRepository(impl: StockRepositoryImpl): StockRepository

    @Binds
    @Singleton
    fun robotRepository(impl: RobotRepositoryImpl): RobotRepository
}
