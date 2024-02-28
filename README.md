FlavorFiesta Android Application
====
A simple recipes app that shows a list and detail screens of recipes
from https://dummyjson.com/recipes.
This is an attempt to follow Clean MVVM architecture and use latest Compose APIs.

## Snapshot

<img src="FlavorFiesta_screen_recording.gif" width="300" alt="">

## Build tools & versions used

- Android Studio Hedgehog | 2023.1.1 Patch 2
- Gradle 8.2.0
- Kotlin 1.9.21
- Android Compile Sdk 34
- Android Minimum Sdk 23

## How to run the app

Open the project in Android Studio, build and run on an emulator or a device.

## Architecture and libraries

- MVVM with ViewModels, Flow and StateFlow
- Compose for UI
- Hilt for dependency injection
- Clean Architecture with a domain layer that has use cases
- Room for persistence
- Compose Navigation
- Kotlin Coroutines
- Coil for image loading
- Retrofit and Gson for networking
- Junit for testing
-

## Code or dependencies that were borrowed from other sources

- [setUpEdgeToEdge](https://github.com/Ibrahimhassan1/FlavorFiesta/blob/9710226cb7c8ffbc71f59c9d8e4bfd0af839c3ff/app/src/main/java/com/softups/flavorfiesta/ui/theme/Theme.kt#L120)
  is borrowed
  from [Material Theming with Jetpack Compose](https://developer.android.com/codelabs/basic-android-kotlin-compose-material-theming#1)
- Custom app icon
  from [freepik.com](https://www.freepik.com/free-vector/cooking-elements-flat_4528487.htm#fromView=search&page=1&position=52&uuid=11519c2d-a82b-411e-997c-94e3a03ccefb)
- To navigate between
  screens [Navigation with compose](https://developer.android.com/jetpack/compose/navigation)
- To support landscape
  mode [Build adaptive layouts](https://developer.android.com/jetpack/compose/layouts/adaptive)
- To generate color schemes: [Material Theme Builder](https://m3.material.io/theme-builder)
- Fonts from [Google Fonts](https://fonts.google.com/)

## General references:

- [Jetpack Compose](https://developer.android.com/jetpack/compose/documentation)
- [Guide to app architecture](https://developer.android.com/topic/architecture)
- [Google Developers Codelabs](https://codelabs.developers.google.com/?cat=Android)
