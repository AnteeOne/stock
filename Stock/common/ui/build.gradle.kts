plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidBase)
    id(Plugins.kotlinKapt)
    id(Plugins.compose)
}

dependencies {
    implementation(Deps.appCompat)
    implementation(Deps.coreKtx)

    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.foundation)

    implementation(Deps.Coroutines.viewModel)
    implementation(Deps.Coroutines.kotlinCore)
}
