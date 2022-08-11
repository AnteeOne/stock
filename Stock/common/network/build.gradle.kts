plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidBase)
    id(Plugins.kotlinKapt)
}

dependencies {
//    implementation(project(Modules.commonMultiCompose))

    api(project(Modules.featureStockDataApi))

    implementation(Deps.appCompat)
    implementation(Deps.coreKtx)

    implementation(Deps.Network.retrofit)
    implementation(Deps.Network.gson)

    implementation(Deps.Dagger.core)
    kapt(Deps.Dagger.compiler)
}
