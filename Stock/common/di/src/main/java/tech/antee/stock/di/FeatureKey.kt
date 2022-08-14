package tech.antee.stock.di

import dagger.MapKey
import tech.antee.stock.multi_compose.Feature
import kotlin.reflect.KClass

@MapKey
annotation class FeatureKey(val value: KClass<out Feature>)
