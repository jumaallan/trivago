extensions.configure<com.android.build.gradle.BaseExtension> {
    compileSdkVersion(AndroidSdk.compileSdkVersion)
    android.buildFeatures.dataBinding = true
    android.buildFeatures.viewBinding = true
    defaultConfig {
        minSdkVersion(AndroidSdk.minSdkVersion)
        targetSdkVersion(AndroidSdk.targetSdkVersion)
        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName
        testInstrumentationRunner = "com.trivago.runner.MockTestRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

   tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>{
       kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
   }
}



dependencies {
    add("implementation",fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    add("implementation",project(BuildModules.coreModule))
    add("implementation",Libraries.kotlinStdLib)
    add("implementation",Libraries.coreKtx)

    // Material and AndroidX
    add("implementation",Libraries.constraintLayout)
    add("implementation",Libraries.appCompat)
    add("implementation",Libraries.swiperefreshlayout)
    add("implementation",Libraries.preference)
    add("implementation",Libraries.material)

    // Room
    add("implementation",Libraries.room)
    add("implementation",Libraries.roomRuntime)
    add("kapt",Libraries.roomCompiler)

    // Coroutines
    add("implementation",Libraries.coroutines)
    add("implementation",Libraries.coroutinesAndroid)

    // DI - KOIN
    add("implementation",Libraries.koin)
    add("implementation",Libraries.koinViewModel)

    // Network - Retrofit, OKHTTP
    add("implementation",Libraries.retrofit)
    add("implementation",Libraries.ohttp)
    add("implementation",Libraries.loggingInterceptor)
    add("implementation",Libraries.gson)

    // Lifecycle
    add("implementation",Libraries.viewModel)
    add("implementation",Libraries.livedata)
    add("implementation",Libraries.lifecycle)
    add("implementation",Libraries.viewModelSavedState)

    // Circle Indicator
    add("implementation",Libraries.circleIndicator)

    // Debug - for debug builds only
    add("implementation",Libraries.timber)
    add("implementation",Libraries.leakCanary)
    add("implementation",Libraries.stetho)

    // UI Tests
    add("androidTestImplementation",TestLibraries.espresso)
    add("androidTestImplementation",TestLibraries.kakao)

    // Instrumentation Tests
    add("androidTestImplementation",TestLibraries.runner)
    add("androidTestImplementation",TestLibraries.rules)
    add("androidTestImplementation",TestLibraries.koinTest)
    add("androidTestImplementation",TestLibraries.androidXJUnit)
    add("androidTestImplementation",TestLibraries.androidXTestCore)
    add("androidTestImplementation",TestLibraries.mockWebServer)
    add("androidTestImplementation",TestLibraries.androidMockK)

    // Unit Tests
    add("testImplementation",TestLibraries.jUnit)
    add("testImplementation",TestLibraries.roomTest)
    add("testImplementation",TestLibraries.koinTest)
    add("testImplementation",TestLibraries.mockK)
    add("testImplementation",TestLibraries.mockWebServer)
    add("testImplementation",TestLibraries.roboelectric)
    add("testImplementation",TestLibraries.mockito)
    add("testImplementation",TestLibraries.truth)
    add("testImplementation",TestLibraries.runner)
    add("testImplementation",TestLibraries.androidXJUnit)
    add("testImplementation",TestLibraries.coroutinesTest)
    add("testImplementation",TestLibraries.archComponentTest)
    add("testImplementation",TestLibraries.liveDataTesting)
}