package tech.antee.stock.di.qualifiers

import tech.antee.stock.di.Dependencies
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class DependenciesKey(val value: KClass<out Dependencies>)
