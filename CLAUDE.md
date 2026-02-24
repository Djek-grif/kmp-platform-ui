# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build the project
./gradlew build

# Run all tests
./gradlew test

# Run tests for a specific module
./gradlew :domain:test
./gradlew :shared:test

# Run a specific test class
./gradlew :domain:testDebugUnitTest --tests "*.SignInUseCaseTest"

# Build Android APK
./gradlew :composeApp:assembleDebug

# Build iOS all configured
./gradlew :shared:assemble

# Clean build
./gradlew clean
```


## Module Architecture

Three main Kotlin modules with a strict layering rule: **dependencies only flow inward** (composeApp → shared → domain).

- **`domain/`** — Pure Kotlin, no platform dependencies. Contains use cases, repository interfaces, and domain models (`DResult<T>`, `ErrorCode`, `UserSession`). Shared across all targets (JVM, Android, iOS).

- **`shared/`** — Platform implementations. Contains repository implementations, data sources, ViewModels, navigation components (Decompose), and Koin DI setup. Exports Decompose to iOS.

- **`composeApp/`** — Android UI entry point. Jetpack Compose screens, `MainActivity`, and Android-specific Koin initialization.

- **`iosApp/`** — Native Xcode/SwiftUI project that integrates the `shared` framework.

## Architecture Pattern: Clean Architecture + MVI

**Layers (outer → inner):**
1. UI screens (Compose for Android and SwiftUI for iOS) dispatch `Action` → ViewModel
2. `BaseViewModel<Action, UiState, Effect>` processes actions, emits `StateFlow<UiState>` and `Flow<Effect>`
3. Use cases (domain layer) are invoked by ViewModels, return sealed result types
4. Repository implementations (shared layer) call data sources, map `ApiResult<T>` → `DResult<T>`

**Key base classes in `shared/src/commonMain`:**
- `MviViewModel` — interface defining `viewState`, `effect`, `action`, `onUIAction()`
- `BaseViewModel` — implementation with `setState {}` and `setEffect {}` helpers
- `ScreenComponent` — Decompose component base with navigator and lifecycle-scoped ViewModel

**Result types:**
- `DResult<R>` (domain) — `Success` / `Error(errorCode: ErrorCode)`
- `ApiResult<T>` (data) — `Success` / `Error` mapped via `toDResult()` extension

## Navigation

Uses **Decompose** with a `DefaultRootComponent` managing a `ChildStack<AppScreen, *>`. Each screen has a corresponding `Component` class that holds its ViewModel (lazy, DI-created via `getViewModel<T>()`). Navigation effects flow from ViewModel → Component → Navigator.

Screens: `Welcome`, `SignIn`, `SignUp`, `Home` (defined as sealed interface `AppScreen`).

## Dependency Injection

**Koin** configured in `shared/src/commonMain/.../di/AppModule.kt`. ViewModels use `factoryOf(::XyzViewModel)` — a new instance per component scope. Repositories and data sources are `singleOf(...)`.

- Android: `initKoin(this)` called in `MainActivity`
- iOS: `KoinInitializer.ios.kt` handles initialization

## Testing

Tests live in `commonTest` source sets. Uses **Mokkery** for mocking (KMP-compatible). See `domain/src/commonTest/.../SignInUseCaseTest.kt` for the established pattern — mock repositories with `mock<T>()`, use `runTest {}` for coroutine tests, and verify behavior with `verify`.

## Key Versions

| Tool | Version |
|------|---------|
| Kotlin | 2.3.0 |
| Compose Multiplatform | 1.9.3 |
| Decompose | 3.4.0 |
| Koin | 4.1.1 |
| Mokkery | 3.2.0 |
| Android compileSdk | 36, minSdk 24 |
| JVM target | Java 11 |

All versions are centralized in `gradle/libs.versions.toml`.
