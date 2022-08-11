plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidBase)
    id(Plugins.compose)
    id(Plugins.kotlinKapt)
}

dependencies {
    implementation(project(Modules.coreUi))
    implementation(project(Modules.coreStrings))
    implementation(project(Modules.commonMultiCompose))
    implementation(project(Modules.commonUiComponents))

    api(project(Modules.featureStockListApi))

    implementation(Deps.appCompat)
    implementation(Deps.coreKtx)
    implementation(Deps.material)
    implementation(Deps.Coroutines.viewModel)
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.material3)
    implementation(Deps.Compose.navigation)
    implementation(Deps.Compose.toolsPreview)
    debugImplementation(Deps.Compose.tools)

    implementation(Deps.Dagger.core)
    kapt(Deps.Dagger.compiler)
}
