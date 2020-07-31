plugins{
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kapt)
}


android {
    compileSdkVersion(AndroidSdk.compileSdkVersion)

    defaultConfig {
        minSdkVersion(AndroidSdk.minSdkVersion)
        targetSdkVersion(AndroidSdk.targetSdkVersion)
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
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

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.coreKtx)
    implementation(Libraries.appCompat)
    implementation(Libraries.crashlytics)
    implementation(Libraries.retrofit)
    implementation(Libraries.ohttp)
    implementation(Libraries.loggingInterceptor)
    implementation(Libraries.koin)
    implementation(Libraries.koinViewModel)
    implementation(Libraries.gson)
    implementation(Libraries.timber)
    implementation(Libraries.material)
    implementation(Libraries.room)
    implementation(Libraries.roomRuntime)
    kapt(Libraries.roomCompiler)

    testImplementation(TestLibraries.jUnit)
    androidTestImplementation(TestLibraries.jUnitTest)
    androidTestImplementation(TestLibraries.espresso)
}