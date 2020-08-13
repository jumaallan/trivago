plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kapt)
    jacoco
}

// apply(from = "https://raw.githubusercontent.com/JakeWharton/SdkSearch/master/gradle/projectDependencyGraph.gradle")
apply (from = "$rootDir/android.gradle.kts")

android {
    compileSdkVersion(AndroidSdk.compileSdkVersion)

    android.buildFeatures.dataBinding = true
    android.buildFeatures.viewBinding = true

    defaultConfig {
        applicationId = "com.trivago"
        vectorDrawables.useSupportLibrary = true
    }

    kapt {
        arguments {
            arg("room.incremental", "true")
        }
    }

    spotless {
        kotlin {
            licenseHeaderFile(
                rootProject.file("spotless/copyright.kt"),
                "^(package|object|import|interface)"
            )
        }
    }

    tasks.dokka {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokka"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}