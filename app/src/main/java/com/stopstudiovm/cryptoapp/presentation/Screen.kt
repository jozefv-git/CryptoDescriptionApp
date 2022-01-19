package com.stopstudiovm.cryptoapp.presentation

// Classes or objects what want to inherit from sealed class must be in the same file
// So bigger control over inheritance, but also good for state representation

// This will help us with routes for navigation
sealed class Screen(val route: String) {
    object CoinListScreen: Screen("coin_list_screen") // CoinListScreen inherits from the Screen
    object CoinDetailScreen: Screen("coin_detail_screen")
}
