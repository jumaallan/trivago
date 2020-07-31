plugins{
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kapt)
}

android {
    compileSdkVersion(AndroidSdk.compileSdkVersion)

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    defaultConfig {
        applicationId ="com.trivago"
        minSdkVersion(AndroidSdk.minSdkVersion)
        targetSdkVersion(AndroidSdk.targetSdkVersion)
        versionCode= AndroidSdk.versionCode
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
    implementation(Libraries.constraintLayout)
    implementation(Libraries.appCompat)
    implementation(project(BuildModules.coreModule))
    implementation(Libraries.material)

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.coreKtx)
    implementation(Libraries.room)
    implementation(Libraries.roomRuntime)
    kapt(Libraries.roomCompiler)
    implementation(Libraries.timber)
    implementation(Libraries.leakCanary)
    implementation(Libraries.stetho)
    implementation(Libraries.koin)
    implementation(Libraries.koinViewModel)

    // tests
    testImplementation(TestLibraries.jUnit)
    androidTestImplementation(TestLibraries.jUnitTest)
    androidTestImplementation(TestLibraries.espresso)
}