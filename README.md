# kmp-platform-ui

Kotlin Multiplatform (KMP) sample project that demonstrates a **mobile architecture setup** with **shared code** and **native platform UI** for **Android + iOS**.

This repository is meant to be a reference implementation of a Kotlin Multiplatform project structured according to the Clean Architecture pattern, with clear separation into layers (such as separate KMP library `domain` and `shared`) while keeping the Android and iOS platform entry points minimal, lightweight, and focused on presentation and integration.

## Goals

- Show a practical **project structure** for KMP apps
- Keep business rules in a platform-agnostic **domain layer**
- Put cross-platform implementations in a **shared** module
- Provide platform shells (with presentation layers):
  - Android app module
  - iOS app module

## Project structure

High-level modules you’ll find in this repo:

- **`domain/`**  
  Pure business logic: entities, use-cases, contracts/interfaces (no platform dependencies).

- **`shared/`**  
  Shared implementation code used by both platforms (e.g., data sources, repositories, platform abstractions, state management with a single shared ViewModel, and navigation).

- **`composeApp/`**  
  Android application module: app entry point, UI composition.

- **`iosApp/`**  
  iOS application module: iOS entry point, SwiftUI and integration with shared code.


## Tech highlights

- Kotlin Multiplatform project targeting **Android + iOS**
- Layered / modular structure (`domain`, `shared`, platform apps)
- Apache-2.0 licensed

## How to run

### Android
1. Open the project in Android Studio (or IntelliJ with KMP support).
2. Select the Android run configuration (typically from `composeApp`).
3. Run on an emulator/device.

### iOS
1. Open the `iosApp` folder in Xcode (or open the generated `.xcodeproj/.xcworkspace`).
2. Select a simulator.
3. Build & run.

## Notes on architecture

This repo is organized to encourage:
- **Separation of concerns**: UI vs domain vs shared implementation
- **Testable domain logic**: keep domain free of platform/UI dependencies
- **Swappable platform implementations** via interfaces + platform adapters

## License

Apache-2.0 — see [LICENSE](LICENSE).
