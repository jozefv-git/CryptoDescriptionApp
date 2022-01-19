package com.stopstudiovm.cryptoapp.data.repository.get_coin

import com.stopstudiovm.cryptoapp.common.Resource
import com.stopstudiovm.cryptoapp.data.remote.dto.toCoin
import com.stopstudiovm.cryptoapp.data.remote.dto.toCoinDetail
import com.stopstudiovm.cryptoapp.data.repository.model.Coin
import com.stopstudiovm.cryptoapp.data.repository.model.CoinDetail
import com.stopstudiovm.cryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

// We need to inject our repository here
class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository // We want to inject interface because that's allow us to be easily replaceable
){
    // Executor
    // We override the operator fun invoke
    // We override invoke function which basically means that we can call our UseCase as it was a function
    // With calling coroutines flow
    // If we will have data then it would be of type List<Coins>...so if it was successful we will just attach list of coins
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>()) // So in our UI we can display our progressBar
            val coin = repository.getCoinById(coinId).toCoinDetail() // So this is our UI Object again
            emit(Resource.Success<CoinDetail>(coin)) // Here will be data what we want to forward to our viewModel that contains our UseCase
        } catch (e: HttpException){ // So if Response start with different number than 2..
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException){ // If our API or repository even cannot communicate to remote API..no internet or whatever
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "Couldn't reach the server. Check your internet connection."))
        }
    }
}