buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Deps.Gradle.gradle)
        classpath(Deps.Gradle.kotlinGradle)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }

    tasks.register<Delete>("clean") {
        delete(rootProject.buildDir)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
