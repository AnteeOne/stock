plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidBase)
    id(Plugins.kotlinKapt)
}

dependencies {
    projectImplementation(Modules.coreUi)
    projectImplementation(Modules.coreStrings)
    projectImplementation(Modules.commonMultiCompose)
    projectImplementation(Modules.commonUiComponents)
    projectImplementation(Modules.commonDomain)
    projectImplementation(Modules.commonUi)
    projectImplementation(Modules.commonDi)

    projectApi(Modules.featureStockRobotApi)

    implementation(Deps.appCompat)
    implementation(Deps.coreKtx)
    implementation(Deps.material)
    implementation(Deps.lifecycleService)
    implementation(Deps.lifecycleRuntimeKtx)
    implementation(Deps.Coroutines.kotlinCore)
    implementation(Deps.Coroutines.android)

    implementation(Deps.Dagger.core)
    kapt(Deps.Dagger.compiler)
}
