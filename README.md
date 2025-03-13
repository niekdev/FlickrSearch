# FlickrSearch - a KMP Android/iOS application

FlickrSearch is a cross-platform application built with Kotlin Multiplatform, sharing business logic and UI across Android and iOS platforms using Compose Multiplatform. The application allows you to search for photos using the Flickr API, which is backed up by paging to allow for infinite scrolling. There's a search history which keeps track of your search terms, this way you can easily jump back in.

The project is multi-modularized and follows clean architecture principles with a presentation-domain-data layer structure to enforce a proper separation of concerns. The chosen architectural pattern is MVVM where use cases are injected into the ViewModels through dependency injection.

https://github.com/user-attachments/assets/1b8dd59b-5f1c-44bc-bd23-3abfab8edb2b

## Tech Stack

### Architecture & Design
- **MVVM** (Model-View-ViewModel) design pattern
- **Clean Architecture** with presentation, domain, and data layers being separate modules
- **Use Cases** for business logic encapsulation

### Technologies and dependencies
- **Kotlin Multiplatform** - For sharing code between platforms
- **Compose Multiplatform** - For shared UI implementation
- **Compose Navigation** - Navigation component for Compose
- **SQLDelight** - Type-safe SQL for Kotlin Multiplatform
- **Koin** - Lightweight dependency injection framework
- **Ktor** - Multiplatform HTTP client
- **Cash App Paging** - Multiplatform paging library
- **Landscapist** - Image loading library optimzed for Compose
- **Napier** - Multiplatform logging library
- **BuildKonfig** - Build configuration for Kotlin Multiplatform
- **KotlinX Serialization** - JSON serialization and deserialization
- **KotlinX Coroutines** - Asynchronous programming with coroutines

### Testing
- **JUnit 5** - Testing framework
- **Mokkery** - Mocking library for Kotlin Multiplatform
- **Turbine** - Testing library for Kotlin Flow
- **Compose UI Testing** - For UI component testing

## Getting Started

### Setup
1. Clone the repository
2. Create a file named `keys.properties` in the root directory of the project
3. Add the following line to the file:
   ```
    FLICKR_API_KEY=123456789
    ```
    Replace `123456789` with your actual Flickr API key which can be obtained [here](https://www.flickr.com/services/api/misc.api_keys.html).
### Building the Project

#### Android app
1. Simply open the repository in Android studio
2. Select your Android emulator/device in the IDE
3. Run `composeApp`
#### iOS app
1. In the repo's root directory, open:
   `iosApp/FlickrSearch/FlickrSearch.xcodeproj`
2. This will launch Xcode
3. Simply click the run button after selecting your simulator
#### Unit tests
1. CD to the repository's root directory in your terminal
2. run `./gradlew allTests`
#### Compose tests
1. Launch Android Studio
2. Open the presentation test files (e.g. `SearchHistoryResultsContentTest`)
3. Click on the green run button next to the test class
4. Choose `Run SearchHistoryResults...`
5. Choose `iosX64` to run the compose tests 

Running the compose tests on an Android emulator requires additional setup, funnily enough. Since the UI is fully shared without any alterations depending on the platform, the test target of choice doesn't matter.
