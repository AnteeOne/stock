package tech.antee.stock.multi_compose.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import tech.antee.stock.multi_compose.CommonProvider
import javax.inject.Singleton

@Singleton
@Component
interface CommonComponent : CommonProvider {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CommonComponent
    }
}
