package tech.antee.stock.data.remote.di

import dagger.Module

@Module(
    includes = [RetrofitModule::class]
)
interface NetworkModule
