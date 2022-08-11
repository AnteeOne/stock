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
include(":features:stock-list:impl")
include(":features:stock-list:api")
include(":features:stock-data:impl")
include(":features:stock-data:api")
include(":common:network")
