package tech.antee.stock.data.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import tech.antee.stock.data.local.dao.StockDao
import tech.antee.stock.data.local.db.StockDatabase
import tech.antee.stock.di.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun stockDatabase(@ApplicationContext context: Context): StockDatabase = Room.databaseBuilder(
        context,
        StockDatabase::class.java,
        "stock-database"
    ).build()

    @Provides
    @Singleton
    fun stockDao(db: StockDatabase): StockDao = db.stockDao()
}
