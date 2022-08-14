package tech.antee.stock.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CoroutineDispatcherQualifier(val dispatcher: CoroutineDispatcherType)

enum class CoroutineDispatcherType {
    Main,
    Io,
    Default,
}
