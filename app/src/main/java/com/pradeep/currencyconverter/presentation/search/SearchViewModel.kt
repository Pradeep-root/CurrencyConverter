package com.pradeep.currencyconverter.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradeep.currencyconverter.core.common.ApiResult
import com.pradeep.currencyconverter.core.common.toUserMessage
import com.pradeep.currencyconverter.domain.model.CurrencyRate
import com.pradeep.currencyconverter.domain.usecase.GetCurrencyRatesUseCase
import com.pradeep.currencyconverter.presentation.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCurrencyRatesUseCase: GetCurrencyRatesUseCase
): ViewModel() {


    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        fetchCurrencyRates()
    }

    fun fetchCurrencyRates() {
        viewModelScope.launch {
            _uiState.value = when (val result = getCurrencyRatesUseCase()) {
                is ApiResult.Error -> SearchUiState.Error(result.exception.toUserMessage())
                is ApiResult.Success -> SearchUiState.Success(result.data)
            }
        }
    }
}

sealed class SearchUiState {
    data object Loading : SearchUiState()
    data class Success(val data: List<CurrencyRate>) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}