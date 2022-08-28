package tech.antee.stock.data.remote.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.antee.stock.data.remote.api.StockNetworkApi
import tech.antee.stock.data.remote.config.NetworkConfig
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun stockNetworkApi(retrofit: Retrofit): StockNetworkApi = retrofit.create(StockNetworkApi::class.java)

    @Provides
    @Singleton
    fun retrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder().build()
}
