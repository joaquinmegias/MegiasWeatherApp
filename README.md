# MegiasWeatherApp

🌤️ **MegiasWeatherApp** is an Android weather app that consumes the OpenWeather API and displays current weather information by city or device location. The project follows an **MVVM / Clean Architecture-like** structure, with clear separation of layers: `domain`, `data`, and `ui`.

---

## 📌 Features

- Select a city from a predefined list.  
- Get weather using the device's current location.  
- Display:
  - Current temperature, min and max temperature  
  - Wind speed (m/s)  
  - Weather description and corresponding icon
- UI state handling: Idle, Loading, Success, Error  
- Placeholders while loading data to improve UX  
- Null or empty values are shown as dashes (`—`) to avoid showing misleading data

---

## 🏗 Architecture

The project is organized following a **MVVM + Clean Architecture simplified** approach:


- **Domain:** Contains repository interfaces, use cases, and pure domain models.  
- **Data:** Implements repositories, maps DTOs to domain models, and handles API communication.  
- **UI:** Jetpack Compose Composables and UI states (`Idle`, `Loading`, `Success`, `Error`).

---

## ⚡ Technologies & Libraries

- Kotlin  
- Jetpack Compose  
- Retrofit + Kotlin Serialization  
- Hilt for dependency injection  
- Coil3 for loading weather icons  
- Coroutines + StateFlow for reactive data handling  

---

## 🚀 How to run the app

1. Clone the repository:

```bash
git clone https://github.com/joaquinmegias/MegiasWeatherApp.git

2. Open in Android Studio and sync Gradle.

3. The API key is hardcoded in build.gradle, so no additional setup is required.

4. Run the app on an emulator or physical device.

📝 Notes

Nullable values from the API (e.g., tempMin) are displayed as dashes (—) to avoid showing default values that may be misleading.

Wind speed is displayed, in m/s.

Weather icons are dynamically loaded from OpenWeather using Coil.

The app respects Clean Architecture principles and separation of concerns.

📁 Commit structure

Local commits were preserved when pushing the project to GitHub.

🔗 Links

OpenWeather API
https://openweathermap.org/current

GitHub repository
https://github.com/joaquinmegias/MegiasWeatherApp
