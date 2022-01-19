package com.stopstudiovm.cryptoapp.presentation.coin_list

import com.stopstudiovm.cryptoapp.data.repository.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""

)
