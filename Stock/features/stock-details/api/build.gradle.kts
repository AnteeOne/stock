plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidBase)
    id(Plugins.compose)
}

dependencies {
    projectImplementation(Modules.commonMultiCompose)

    implementation(Deps.appCompat)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.navigation)
}
