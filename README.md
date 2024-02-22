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

- MVVM with ViewModels, StateFlow
- Compose for UI
- Hilt for dependency injection
- Clean Architecture with a domain layer that has use cases
- Room for persistence
- Compose Navigation
- Kotlin Coroutines
- Coil for image loading
- Retrofit and Gson for networking
- Junit for testing