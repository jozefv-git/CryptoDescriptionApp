package com.stopstudiovm.cryptoapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stopstudiovm.cryptoapp.common.Resource
import com.stopstudiovm.cryptoapp.data.repository.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

// Our ViewModel, and here we will inject dependencies
@HiltViewModel
class CoinListViewModel @Inject constructor(
    // We need one dependencies
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel(){
    // We need private and public variable
    // We don't want to modify the sate in our composable
    private val _state = mutableStateOf(CoinListState())
    // This is immutable - cannot be changed
    val state: State<CoinListState> = _state
    // So only View Model will be able to touch this

    // Fun that provides data to our stateObject
    private fun getCoins() {
        // I can call this class like a function because we override the Invoke fun and it will return flow
        getCoinsUseCase().onEach {
            // on each resource object
            result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CoinListState(
                        coins = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = CoinListState(
                        error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope) // Coroutine start same as launch
    }

    // This is called when object is created - initializer
    init {
        getCoins()
    }
}