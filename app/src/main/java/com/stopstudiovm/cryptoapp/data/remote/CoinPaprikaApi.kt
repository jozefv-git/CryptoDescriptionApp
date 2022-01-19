package com.stopstudiovm.cryptoapp.data.remote

import com.stopstudiovm.cryptoapp.data.remote.dto.CoinDetailDto
import com.stopstudiovm.cryptoapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}") // This is how we define path url parameter
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto

}