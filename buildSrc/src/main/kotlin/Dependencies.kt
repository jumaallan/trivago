object Versions {
    // Material and androidX
    const val material = "1.3.0-alpha02"
    const val appCompat = "1.3.0-alpha02"
    const val constraintLayout = "2.0.1"
    const val swiperefreshlayout = "1.2.0-alpha01"
    const val preference = "1.1.1"

    // Firebase
    const val crashlytics = "17.2.1"

    //Networking
    const val retrofit = "2.9.0"
    const val okhttp = "4.8.1"
    const val loggingInterceptor = "4.8.1"

    //DI - KOIN
    const val koin = "2.1.5"

    // Room db
    const val room = "2.3.0-alpha02"

    // Lifecycle
    const val lifecycle = "2.3.0-alpha07"

    // Coroutines
    const val coroutines = "1.3.9"

    // Logging - debug builds
    const val timber = "4.7.1"
    const val stetho = "1.5.1"
    const val leakCanary = "2.4"

    // Kotlin
    const val kotlinVersion = "1.4.0"
    const val coreKtx = "1.5.0-alpha02"

    // Circle Indicator
    const val circleIndicator = "2.1.4"

    // Gradle Plugins
    const val ktlint = "9.3.0"
    const val detekt = "1.12.0"
    const val spotless = "5.3.0"
    const val dokka = "1.4.0-rc-24"
    const val gradleVersionsPlugin = "0.29.0"

    // tests
    const val junit = "4.13"
    const val junitTest = "1.1.2"
    const val espresso = "3.3.0"
    const val roboelectric = "4.4-beta-1"
    const val androidXJUnit = "1.1.1"
    const val truth = "1.0.1"
    const val mockWebServer = "4.8.1"
    const val androidXTestCore = "1.3.0"
    const val runner = "1.3.0"
    const val rules = "1.3.0"
    const val archComponentTest = "2.1.0"
    const val kakao = "2.3.4"
    const val mockK = "1.10.0"
    const val liveDataTesting = "1.1.2"
}

object BuildPlugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinAndroidExtensions = "org.jetbrains.kotlin.android.extensions"
    const val androidLibrary = "com.android.library"
    const val kapt = "kotlin-kapt"
    const val ktlintPlugin = "org.jlleitschuh.gradle.ktlint"
    const val detektPlugin = "io.gitlab.arturbosch.detekt"
    const val spotlessPlugin = "com.diffplug.spotless"
    const val dokkaPlugin = "org.jetbrains.dokka"
    const val gradleVersionsPlugin = "com.github.ben-manes.versions"
}

object Libraries {
    // androidX and Material
    const val material = "com.google.android.material:material:${Versions.material}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val preference = "androidx.preference:preference-ktx:${Versions.preference}"
    const val swiperefreshlayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // Firebase
    const val crashlytics = "com.google.firebase:firebase-crashlytics:${Versions.crashlytics}"

    // Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val ohttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    // DI - KOIN
    const val koin = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    // Room db
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // Lifecycle
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val viewModelSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"

    // Coroutines
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Logging - debug builds
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    // Circle Indicator
    const val circleIndicator = "me.relex:circleindicator:${Versions.circleIndicator}"
}

object TestLibraries {
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val jUnit = "junit:junit:${Versions.junit}"
    const val jUnitTest = "androidx.test.ext:junit:${Versions.junitTest}"
    const val roboelectric = "org.robolectric:robolectric:${Versions.roboelectric}"
    const val androidXJUnit = "androidx.test.ext:junit:${Versions.androidXJUnit}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"
    const val androidXTestCore = "androidx.test:core:${Versions.androidXTestCore}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val rules = "androidx.test:rules:${Versions.rules}"
    const val koinTest = "org.koin:koin-test:${Versions.koin}"
    const val roomTest = "androidx.room:room-testing:${Versions.room}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val archComponentTest =
        "androidx.arch.core:core-testing:${Versions.archComponentTest}"
    const val kakao = "com.agoda.kakao:kakao:${Versions.kakao}"
    const val mockK = "io.mockk:mockk:${Versions.mockK}"
    const val androidMockK = "io.mockk:mockk-android:${Versions.mockK}"
    const val liveDataTesting = "com.jraska.livedata:testing-ktx:${Versions.liveDataTesting}"
}

object BuildModules {
    const val coreModule = ":core"
}

object AndroidSdk {
    const val minSdkVersion = 21
    const val compileSdkVersion = 30
    const val targetSdkVersion = compileSdkVersion
    const val versionCode = 1
    const val versionName = "1.0"
}
