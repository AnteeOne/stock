pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
rootProject.name = "Stock"
include(":app")
include(":core:ui")
include(":core:strings")
include(":common:multi-compose")
include(":common:ui-components")
