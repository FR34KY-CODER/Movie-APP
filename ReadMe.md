# 🎬 Android Netflix-Style Movie App
---

A full-fledged Netflix-inspired Android app built with modern Kotlin and Jetpack components, featuring Firebase authentication and TMDB API integration.

---

## ✨ Features

### 🔐 Authentication
- Firebase Email/Password Authentication
- Secure sign-in flow
- Forgot password functionality

### 🎥 Movie Discovery
- Popular, Trending, and Top Rated movies sections
- Horizontal scrollable movie lists
- Beautiful movie posters with Glide image loading

### 🍿 Movie Details
- Detailed movie information screen
- Backdrop image, rating, and overview
- Responsive layout for all screen sizes

### 🛠️ Tech Stack
- **100% Kotlin**
- **Firebase Realtime Database** for user data
- **TMDB API** integration
- **Jetpack Components** (ViewModel, LiveData, Navigation)
- **Retrofit** for network calls
- **Coroutines** for asynchronous operations
- **Glide** for image loading
- **Material Design** components

## 🚀 Getting Started

### Prerequisites
- Android Studio (latest version)
- Firebase account
- TMDB API key

### Installation
1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/netflix-style-app.git
   ```
2. Set up Firebase
 - Create a Firebase project at Firebase Console
 - Enable Authentication (Email/Password method)
 - Enable Realtime Database
 - Add your Android app and download google-services.json
 - Place it in app/ directory
3. Get TMDB API key
 - Register at TMDB Developer Portal
 - Add your API key to local.properties:
 - ```bash
   TMDB_API_KEY=your_api_key_here
   ```
 - Build and run the app!

---

## 📂 Project Structure
```bash
netflix-style-app/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/netflixstyle/
│   │   │   │   ├── auth/
│   │   │   │   │   ├── SignInActivity.kt
│   │   │   │   │   └── ForgotPasswordActivity.kt
│   │   │   │   ├── home/
│   │   │   │   │   ├── HomeActivity.kt
│   │   │   │   │   ├── MovieAdapter.kt
│   │   │   │   │   └── MovieViewHolder.kt
│   │   │   │   ├── details/
│   │   │   │   │   └── MovieDetailActivity.kt
│   │   │   │   ├── model/
│   │   │   │   │   ├── Movie.kt
│   │   │   │   │   └── User.kt
│   │   │   │   ├── network/
│   │   │   │   │   ├── TMDBService.kt
│   │   │   │   │   ├── ApiClient.kt
│   │   │   │   │   └── Response.kt
│   │   │   │   └── utils/
│   │   │   │       ├── Extensions.kt
│   │   │   │       └── Constants.kt
│   │   │   └── res/
│   │   │       ├── layout/
│   │   │       ├── drawable/
│   │   │       └── values/
│   ├── build.gradle.kts
├── build.gradle.kts
└── settings.gradle.kts
```

---

## 🤝 Contributing
Contributions are welcome! Please follow these steps:
 - Fork the project
 - Create your feature branch ```bash git checkout -b feature/AmazingFeature ```
 - Commit your changes ```bash git commit -m 'Add some AmazingFeature'```
 - Push to the branch ```bash git push origin feature/AmazingFeature ```
 - Open a Pull Request

---

## 📝 TODO (Future Enhancements)

 - Add bottom navigation like Netflix
 - Implement user profiles with images
 - Add video trailers playback
 - Introduce offline caching
 - Add favorites/watchlist functionality

---

## 🙏 Acknowledgments
 - TMDB for their awesome API
 - Firebase for backend services
 - Jetpack libraries for making Android development easier

---

Built with ❤️ by FR34KY-CODER
