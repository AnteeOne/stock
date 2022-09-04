plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidKotlin)
    id(Plugins.androidBase)
}

dependencies {
    projectImplementation(Modules.commonDomain)
    implementation(Deps.Coroutines.kotlinCore)
    implementation(Deps.Coroutines.android)
}
