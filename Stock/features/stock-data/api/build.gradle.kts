plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidBase)
}

dependencies {
//    implementation(project(Modules.commonMultiCompose))

    implementation(Deps.appCompat)
    implementation(Deps.coreKtx)
    implementation(Deps.material)

    implementation(Deps.Network.retrofit)
    implementation(Deps.Network.gson)
}
