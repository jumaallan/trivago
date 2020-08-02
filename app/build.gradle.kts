plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kapt)
}

apply(from = "https://raw.githubusercontent.com/JakeWharton/SdkSearch/master/gradle/projectDependencyGraph.gradle")

android {
    compileSdkVersion(AndroidSdk.compileSdkVersion)

    dataBinding.isEnabled = true
    viewBinding.isEnabled = true

    defaultConfig {
        applicationId = "com.trivago"
        minSdkVersion(AndroidSdk.minSdkVersion)
        targetSdkVersion(AndroidSdk.targetSdkVersion)
        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
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

kapt {
    arguments {
        arg("room.incremental", "true")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(BuildModules.coreModule))
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.coreKtx)

    // Material and AndroidX
    implementation(Libraries.constraintLayout)
    implementation(Libraries.appCompat)
    implementation(Libraries.swiperefreshlayout)
    implementation(Libraries.material)

    // Room
    implementation(Libraries.room)
    implementation(Libraries.roomRuntime)
    kapt(Libraries.roomCompiler)

    // Coroutines
    implementation(Libraries.coroutines)
    implementation(Libraries.coroutinesAndroid)

    // DI - KOIN
    implementation(Libraries.koin)
    implementation(Libraries.koinViewModel)

    // Lifecycle
    implementation(Libraries.viewModel)
    implementation(Libraries.livedata)
    implementation(Libraries.lifecycle)
    implementation(Libraries.viewModelSavedState)

    // Circle Indicator
    implementation(Libraries.circleIndicator)

    // debug
    implementation(Libraries.timber)
    implementation(Libraries.leakCanary)
    implementation(Libraries.stetho)

    // tests
    testImplementation(TestLibraries.jUnit)
    androidTestImplementation(TestLibraries.jUnitTest)
    androidTestImplementation(TestLibraries.espresso)
}