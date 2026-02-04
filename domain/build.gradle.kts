import com.android.build.api.dsl.androidLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinMultiplatformLibrary)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    jvm("desktop")
    androidLibrary {
        namespace = "com.djekgrif.nativeuisimple.domain"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        aarMetadata.minAgpVersion = libs.versions.agp.get()
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "DomainModule"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
//                implementation(project(":libs:logger"))
                implementation(libs.coroutines.core)
//                implementation(libs.kotlinx.serialization.json)
//                implementation(libs.kotlinx.datetime)
            }
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
