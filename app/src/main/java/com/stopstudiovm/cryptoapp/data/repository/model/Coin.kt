package com.stopstudiovm.cryptoapp.data.repository.model

// Data class for our coin in the list

data class Coin(
    val id: String,
    val is_active: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)
