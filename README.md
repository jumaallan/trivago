## \[ 🚧 Work in progress 👷‍♀️⛏👷🔧️🚧 \] Trivago Interview Solution


Delete the .git

👀  Writing Trivago Interview Solution App using [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/), in 100% Kotlin, using Android Jetpack Components. 

### Prerequisites 

Before every commit, make sure you run the following commands:

* ./gradlew ktlintFormat

* ./gradlew ktlintCheck

* ./gradlew detekt

* ./gradlew spotlessApply

### Background

Implement a small but scalable (!) app which interacts with the open Star Wars API at https://swapi.dev/.

The app should contain 2 main areas:
• Character Search (Home Screen)
• Character Details

The following attributes should be displayed for the character details:
• name
• birth year
• height (in cm and feet/inches)
• name (species)
• language (species)
• homeworld (species)
• population (planets)
• films (movies the character appeared in)
• opening crawl (detailed description of each movie)

On app start, the user lands on the character search screen.

The user can search for characters from the Star Wars universe. The result of the search should display a character list.

When tapping on a character, details are displayed as defined above.

## Tech-stack

Min API level is set to [`16`](https://android-arsenal.com/api?level=16), so the presented approach is suitable for over
[98% of devices](https://developer.android.com/about/dashboards) running Android. This project takes advantage of many
popular libraries and tools of the Android ecosystem. Most of the libraries are in the stable version.

* Tech-stack
    * [Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
    * [KOIN](https://insert-koin.io/) - dependency injection
    * [Retrofit](https://square.github.io/retrofit/) - networking
    * [Jetpack](https://developer.android.com/jetpack)
        * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - notify views about database change
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform action when lifecycle state changes
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
    * [Coil](https://github.com/coil-kt/coil) - image loading library with Kotlin idiomatic API
    * [Stetho](http://facebook.github.io/stetho/) - application debugging tool

* Architecture
    * MVVM - application level
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) plugin)
* Tests
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit](https://junit.org/junit4/))
    * [Mockito](https://github.com/mockito/mockito) + [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin)
    * [Kluent](https://github.com/MarkusAmshove/Kluent)
* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
    * Custom tasks
    * Plugins ([Ktlint](https://github.com/JLLeitschuh/ktlint-gradle), [Detekt](https://github.com/arturbosch/detekt#with-gradle), [Versions](https://github.com/ben-manes/gradle-versions-plugin), [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args))


## Getting started

There are a few ways to open this project.

### Android Studio

1. Android Studio -> File -> New -> From Version control -> Git
2. Enter `https://github.com/jumaallan/trivago.git` into URL field

### Command line + Android Studio

1. Run `git clone https://github.com/jumaallan/trivago.git`
2. Android Studio -> File -> Open

## License
```
MIT License

Copyright (c) 2020 Juma Allan

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and 
associated documentation files (the "Software"), to deal in the Software without restriction, including 
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to 
the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial 
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN 
NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```