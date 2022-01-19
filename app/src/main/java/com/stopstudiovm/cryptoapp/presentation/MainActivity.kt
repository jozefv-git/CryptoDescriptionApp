package com.stopstudiovm.cryptoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stopstudiovm.cryptoapp.presentation.coin_detail.components.CoinDetailScreen
import com.stopstudiovm.cryptoapp.presentation.coin_list.components.CoinListScreen
import com.stopstudiovm.cryptoapp.presentation.ui.theme.CryptoAppTheme
import dagger.hilt.android.AndroidEntryPoint

// We need AndroidEntryPoint for Dagger Hilt to inject dependencies into this activity or sub composable  which we need for view model
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoAppTheme() {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController,
                    startDestination = Screen.CoinListScreen.route){
                        composable(
                            route = Screen.CoinListScreen.route
                        ) {
                            CoinListScreen(navController) // Just screen which selected composable represent
                        }
                        // This screen needs a parameter navigation argument which is coinID
                        composable(
                            route = Screen.CoinDetailScreen.route + "/{coinId}"
                        ) {
                            CoinDetailScreen()// Just screen which selected composable represent
                        }
                    }


                }
            }
        }
    }
}