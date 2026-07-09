package com.pradeep.currencyconverter.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradeep.currencyconverter.core.common.ApiResult
import com.pradeep.currencyconverter.core.common.toUserMessage
import com.pradeep.currencyconverter.domain.model.CurrencyRate
import com.pradeep.currencyconverter.domain.usecase.GetCurrencyRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchCurrencyRates()
    }

    fun fetchCurrencyRates() {
        viewModelScope.launch {
            _uiState.value = when (val result = getCurrencyRatesUseCase("EUR")) {
                is ApiResult.Error -> HomeUiState.Error(result.exception.toUserMessage())
                is ApiResult.Success -> HomeUiState.Success(result.data)
            }
        }
    }

}

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(val data: List<CurrencyRate>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}