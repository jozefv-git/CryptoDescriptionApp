package com.stopstudiovm.cryptoapp.data.repository

import com.stopstudiovm.cryptoapp.data.remote.CoinPaprikaApi
import com.stopstudiovm.cryptoapp.data.remote.dto.CoinDetailDto
import com.stopstudiovm.cryptoapp.data.remote.dto.CoinDto
import com.stopstudiovm.cryptoapp.domain.repository.CoinRepository
import javax.inject.Inject

// Inject dependencies here
class CoinRepositoryImplementation @Inject constructor(

    //Our API instance
    private val api: CoinPaprikaApi

) : CoinRepository { // Now we need to implement our repository
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}