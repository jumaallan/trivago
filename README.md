## \[ 🚧 Work in progress 👷‍♀️⛏👷🔧️🚧 \] Trivago Interview Solution

👀  Writing Trivago Interview Solution App using [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/), in 100% Kotlin, using Android Jetpack Components. 

### Prerequisites 

Before every commit, make sure you run the following commands:

```shell script
./gradlew ktlintFormat

./gradlew ktlintCheck

./gradlew detekt

./gradlew spotlessApply
```

Refer to this [issue](https://github.com/gradle/gradle/issues/10248), if you get any issues running the lint commands on the terminal :rocket:

### Background

Implement a small but scalable app which interacts with the open [Star Wars API](https://swapi.dev/). The app should contain 2 main areas:

- Character Search (Home Screen)
- Character Details

The following attributes should be displayed for the character details:

- name
- birth year
- height (in cm and feet/inches)
- name (species)
- language (species)
- homeworld (species)
- population (planets)
- films (movies the character appeared in)
- opening crawl (detailed description of each movie)

On app start, the user lands on the character search screen. The user can search for characters from the Star Wars universe. The result of the search should display a character list. When tapping on a character, details are displayed as defined above.

## Tech-stack

* Tech-stack
    * [Kotlin](https://kotlinlang.org/) 
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
    * [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - handle the stream of data asynchronously that executes sequentially.
    * [KOIN](https://insert-koin.io/) - a pragmatic lightweight dependency injection framework
    * [Retrofit](https://square.github.io/retrofit/) - a type-safe REST client for Android
    * [Jetpack](https://developer.android.com/jetpack)
        * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - is an observable data holder
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform action when lifecycle state changes
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
    * [Stetho](http://facebook.github.io/stetho/) - application debugging tool
    * [Timber](https://github.com/JakeWharton/timber) - a highly extensible android logger

* Architecture
    * MVVM - application level
* Tests
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit](https://junit.org/junit4/))
    * [Mockito](https://github.com/mockito/mockito) + [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin)
    * [Kluent](https://github.com/MarkusAmshove/Kluent)
* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - For reference purposes, here's an [article explaining the migration](https://medium.com/@evanschepsiror/migrating-to-kotlin-dsl-4ee0d6d5c977)
    * Plugins 
        * [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle)
        * [Detekt](https://github.com/arturbosch/detekt#with-gradle) 
        * [Spotless](https://github.com/diffplug/spotless) 
        * [Dokka](https://github.com/Kotlin/dokka)


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
