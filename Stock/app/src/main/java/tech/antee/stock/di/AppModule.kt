package tech.antee.stock.di

import dagger.Module
import tech.antee.stock.data.di.DataModule

@Module(
    includes = [DataModule::class]
)
interface AppModule
