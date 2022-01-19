# CryptoDescriptionApp

CryptoDescriptionApp is basic Android Native Application with Clean Architecture and MVVM + usage of Jetpack Compose. 
It contains 2 screens. First one with list of the clickable coins and second one with detailed description of the coin + status if the coin is active. All data are fetched from the API.

<img src="https://i.ibb.co/60FmMJW/Screenshot-20220119-101730-Crypto-App.jpg" width="300" height="600">
<img src="https://i.ibb.co/dDGBr36/Screenshot-20220119-101739-Crypto-App.jpg" width="300" height="600">


## Features



- Jetpack Composable
- API connection with CoinPaprika
- Navigation between pages and pasing coinId
- Clickable Items

## Used
- Kotlin
- CoinGecko API
- Retrofit
- Dagger Hilt
- Coroutines

## Support Links
- https://coinpaprika.com/api/
- https://square.github.io/retrofit/
- https://dagger.dev/hilt/
- https://www.youtube.com/watch?v=EF33KmyprEQ



## Dependencies

Retrofit

```bash
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation "com.squareup.okhttp3:okhttp:5.0.0-alpha.2"
    implementation "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"
```

Coroutines

```bash
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
```
Dagger Hilt

```bash
    implementation "com.google.dagger:hilt-android:2.39.1"
    kapt "com.google.dagger:hilt-compiler:2.39.1"
    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    kapt "androidx.hilt:hilt-compiler:1.0.0"
    implementation 'androidx.hilt:hilt-navigation-compose:1.0.0-rc01'
```

Plugins

```bash
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'
}
```

## Notes
Aplication was finished with help of folowing this tutorial: 
- https://www.youtube.com/watch?v=EF33KmyprEQ
