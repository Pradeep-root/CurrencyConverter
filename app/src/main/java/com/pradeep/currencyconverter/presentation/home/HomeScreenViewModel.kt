package com.pradeep.currencyconverter.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradeep.currencyconverter.core.common.ApiResult
import com.pradeep.currencyconverter.core.common.PreferenceManager
import com.pradeep.currencyconverter.core.common.toUserMessage
import com.pradeep.currencyconverter.domain.model.ConverterData
import com.pradeep.currencyconverter.domain.usecase.ConvertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val convertUseCase: ConvertUseCase,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private var amount: Double = 1.0
    private var base: String = preferenceManager.get("base", "EUR")
    private var quote: String = preferenceManager.get("quote", "USD")

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        convertRate()
    }

    fun convertRate() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            _uiState.value = when (val result = convertUseCase(amount, base, quote)) {
                is ApiResult.Error -> HomeUiState.Error(result.exception.toUserMessage())
                is ApiResult.Success -> HomeUiState.Success(result.data)
            }
        }
    }

    fun updateAmount(newAmount: Double) {
        amount = newAmount
        convertRate()
    }

    fun updateBaseAndQuote(newBase: String, newQuote: String) {
        base = newBase
        quote = newQuote
        preferenceManager.save("base", base)
        preferenceManager.save("quote", quote)
        convertRate()
    }
}

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(val data: ConverterData) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}