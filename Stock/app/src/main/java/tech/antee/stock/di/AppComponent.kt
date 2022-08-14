package tech.antee.stock.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import tech.antee.stock.App
import tech.antee.stock.di.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, FeaturesModule::class]
)
interface AppComponent : AppProvider {

    fun inject(app: App)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            @ApplicationContext
            context: Context
        ): AppComponent
    }
}
