package tech.antee.stock

import android.app.Application
import tech.antee.stock.di.AppProvider
import tech.antee.stock.di.DaggerAppComponent
import tech.antee.stock.di.DependenciesHolder
import tech.antee.stock.di.DependenciesMap
import javax.inject.Inject

class App : Application(), DependenciesHolder {

    @Inject
    override lateinit var dependenciesMap: DependenciesMap

    lateinit var appProvider: AppProvider

    override fun onCreate() {
        super.onCreate()
        appProvider = DaggerAppComponent.factory().create(this).apply {
            inject(this@App)
        }
    }
}

val Application.appProvider: AppProvider
    get() = (this as App).appProvider
