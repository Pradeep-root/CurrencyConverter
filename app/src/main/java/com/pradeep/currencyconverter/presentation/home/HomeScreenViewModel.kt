package com.pradeep.currencyconverter.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradeep.currencyconverter.core.common.ApiResult
import com.pradeep.currencyconverter.core.common.PreferenceManager
import com.pradeep.currencyconverter.core.common.toUserMessage
import com.pradeep.currencyconverter.domain.model.ConverterData
import com.pradeep.currencyconverter.domain.usecase.GetExchangeRateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.RoundingMode
import javax.inject.Inject

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(val data: ConverterData, val amount: String) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private var amount: Double = 1.0
    private var base: String = preferenceManager.get("base", "EUR")
    private var quote: String = preferenceManager.get("quote", "INR")
    private lateinit var converterData: ConverterData

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchRate()
    }


    private fun calculateTotal() {
        val total = (amount * converterData.rate)
            .toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP)
            .toDouble()

        _uiState.value = HomeUiState.Success(
            converterData.copy(total = total),
            amount.toString()
        )
    }

    fun fetchRate() {
        viewModelScope.launch {
            when (val result = getExchangeRateUseCase(base, quote)) {
                is ApiResult.Error -> _uiState.value =
                    HomeUiState.Error(result.exception.toUserMessage())

                is ApiResult.Success -> {
                    converterData = result.data
                    calculateTotal()
                }
            }
        }
    }

    fun updateAmount(newAmount: Double) {
        amount = newAmount
        calculateTotal()
    }

    fun updateBaseAndQuote(newBase: String, newQuote: String) {
        base = newBase
        quote = newQuote
        preferenceManager.save("base", base)
        preferenceManager.save("quote", quote)
        fetchRate()
    }

    fun swapCurrencies() {
        val tempBase = base
        base = quote
        quote = tempBase
        updateBaseAndQuote(base, quote)
    }
}