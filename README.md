# MedEco

### Technical Details:
- UI: Jetpack Compose for a declarative UI.
- Architecture: MVI ensures unidirectional data flow and separation of concerns.
- Layers: Presentation, Domain, and Data layers for modularity and maintainability.

### Key Technologies:

- Jetpack Compose: Modern UI toolkit.
- Coroutines: Asynchronous programming and concurrency.
- Koin: Dependency Injection.
- Ktor: Networking library for API calls.
- Room Database: (Optional) Local data persistence if needed.

## Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK 8 or higher installed.
- **Android Studio**: Download and install the latest version of [Android Studio](https://developer.android.com/studio).
- **Android SDK**: Make sure you have the necessary SDKs installed via the SDK Manager in Android Studio.
- **Gradle**: The project uses Gradle for build automation. Android Studio comes with an embedded Gradle distribution.

## Setup

1. **Clone the repository**:
    ```sh
    git clone https://github.com/Shifak-Health/shifak-mobile.git
    cd shifak
    ```

2. **Open the project in Android Studio**:
    - Launch Android Studio.
    - Select `Open an existing project`.
    - Navigate to the cloned repository directory and select it.

3. **Sync the project with Gradle**:
    - Android Studio should automatically prompt you to sync the project with Gradle files. If not, you can manually sync by clicking on `File > Sync Project with Gradle Files`.

## Running the App

1. **Connect an Android device or start an emulator**:
    - You can use a physical device connected via USB or start an Android emulator from Android Studio.

2. **Build and install the app**:
    - Open the terminal in Android Studio or use your system terminal.
    - Navigate to the project root directory.
    - Run the following command to build and install the app on the connected device or emulator:
      ```sh
      ./gradlew installDebug
      ```

3. **Run the app**:
    - After the app is installed, you can run it directly from Android Studio by clicking the `Run` button or by using the following command:
      ```sh
      ./gradlew connectedDebugAndroidTest
      ```

## Testing

To run the tests, use the following command:
```sh
./gradlew test
