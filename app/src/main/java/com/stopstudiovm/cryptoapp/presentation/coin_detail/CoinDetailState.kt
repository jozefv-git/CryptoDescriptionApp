package com.stopstudiovm.cryptoapp.presentation.coin_detail

import com.stopstudiovm.cryptoapp.data.repository.model.Coin
import com.stopstudiovm.cryptoapp.data.repository.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null, // Default it will be null
    val error: String = ""

)
