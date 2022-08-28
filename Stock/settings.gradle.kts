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
include(":common:ui")
include(":common:domain")
include(":common:data")
include(":common:di")
include(":features:stock-details:impl")
include(":features:stock-details:api")
