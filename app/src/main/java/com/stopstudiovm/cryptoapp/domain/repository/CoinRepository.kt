package com.stopstudiovm.cryptoapp.domain.repository

import com.stopstudiovm.cryptoapp.data.remote.dto.CoinDetailDto
import com.stopstudiovm.cryptoapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}