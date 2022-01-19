package com.stopstudiovm.cryptoapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stopstudiovm.cryptoapp.common.Constants
import com.stopstudiovm.cryptoapp.common.Resource
import com.stopstudiovm.cryptoapp.data.repository.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

// Our ViewModel, and here we will inject our UseCases that we inject in our constructor
@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    // We need one dependencies
    private val getCoinUseCase: GetCoinUseCase,
    // Saved state handle - bundle which contains information about the saved state
    // We can use it from restore app from process dead etc. and it also contains navigation parameters
    // Which can be use for pass the data for example when we click on something we can send id somewhere...and that's what we need
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    // We need private and public variable
    // We don't want to modify the sate in our composable
    private val _state = mutableStateOf(CoinDetailState())
    // This is immutable - cannot be changed
    // So state will contain corresponding data from our Api when we visit the screen
    val state: State<CoinDetailState> = _state
    // So only View Model will be able to touch this

    // Fun that provides data to our stateObject
    private fun getCoin(coinId: String) {
        // I can call this class like a function because we override the Invoke fun and it will return flow
        // Check what kind of resource it is and based on that, update our state that will then be exposed to our composable
        getCoinUseCase(coinId).onEach {
            // on each resource object
            result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CoinDetailState(
                        coin = result.data
                    )
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(
                        error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope) // Coroutine start same as launch
    }

    // This is called when object is created - initializer
    init {
        // We will get the id after we click on the button...it will be passed here via navigation
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { // When is not null..do this
            coinId ->
            getCoin(coinId)

        }
    }
}