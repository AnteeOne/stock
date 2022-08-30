plugins {
    id(Plugins.androidLibrary)
    id(Plugins.androidBase)
}

dependencies {
    projectImplementation(Modules.commonMultiCompose)

    implementation(Deps.appCompat)
}
