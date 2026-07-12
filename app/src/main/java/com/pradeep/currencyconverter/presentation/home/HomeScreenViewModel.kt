package com.pradeep.currencyconverter.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradeep.currencyconverter.core.common.ApiResult
import com.pradeep.currencyconverter.core.common.PreferenceManager
import com.pradeep.currencyconverter.core.common.toUserMessage
import com.pradeep.currencyconverter.domain.model.ConverterData
import com.pradeep.currencyconverter.domain.model.CurrencyRate
import com.pradeep.currencyconverter.domain.usecase.GetExchangeRateUseCase
import com.pradeep.currencyconverter.domain.usecase.GetHistoricalRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.time.LocalDate
import javax.inject.Inject

enum class TimeRange(val label: String, val days: Int) {
    ONE_MONTH("1M", 30),
    THREE_MONTHS("3M", 90),
    SIX_MONTHS("6M", 180),
    ONE_YEAR("1Y", 365)
}

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(
        val data: ConverterData,
        val amount: String,
        val historicalData: List<CurrencyRate> = emptyList(),
        val selectedRange: TimeRange = TimeRange.ONE_MONTH
    ) : HomeUiState()

    data class Error(val message: String) : HomeUiState()
}

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    private val getHistoricalRatesUseCase: GetHistoricalRatesUseCase,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private var amount: String = "1.0"
    private var base: String = preferenceManager.get("base", "EUR")
    private var quote: String = preferenceManager.get("quote", "INR")
    private lateinit var converterData: ConverterData

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchRate()
        fetchHistoricalRates()
    }

    private fun calculateTotal() {
        val numericAmount = amount.toDoubleOrNull() ?: 0.0
        val total = (numericAmount * converterData.rate)
            .toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP)
            .toDouble()

        val currentState = _uiState.value
        val currentHistoricalData = (currentState as? HomeUiState.Success)?.historicalData ?: emptyList()
        val currentRange = (currentState as? HomeUiState.Success)?.selectedRange ?: TimeRange.ONE_MONTH

        _uiState.value = HomeUiState.Success(
            data = converterData.copy(total = total),
            amount = amount,
            historicalData = currentHistoricalData,
            selectedRange = currentRange
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

    fun fetchHistoricalRates() {
        viewModelScope.launch {
            when (val result = getHistoricalRatesUseCase(
                from = getStartDateOneYearAgo(),
                base = base,
                quote = quote
            )) {
                is ApiResult.Error -> _uiState.value =
                    HomeUiState.Error(result.exception.toUserMessage())

                is ApiResult.Success -> {
                    val currentState = _uiState.value
                    if (currentState is HomeUiState.Success) {
                        _uiState.value = currentState.copy(historicalData = result.data)
                    }
                }
            }
        }
    }

    fun updateAmount(newAmount: String) {
        amount = newAmount
        calculateTotal()
    }

    fun updateBase(newBase: String) {
        base = newBase
        preferenceManager.save("base", base)
        fetchRate()
        fetchHistoricalRates()
    }

    fun updateQuote(newQuote: String) {
        quote = newQuote
        preferenceManager.save("quote", quote)
        fetchRate()
        fetchHistoricalRates()
    }

    fun updateBaseAndQuote(newBase: String, newQuote: String) {
        base = newBase
        quote = newQuote
        preferenceManager.save("base", base)
        preferenceManager.save("quote", quote)
        fetchRate()
        fetchHistoricalRates()
    }

    fun swapCurrencies() {
        val tempBase = base
        base = quote
        quote = tempBase
        updateBaseAndQuote(base, quote)
    }

    fun updateTimeRange(range: TimeRange) {
        val currentState = _uiState.value
        if (currentState is HomeUiState.Success) {
            _uiState.value = currentState.copy(selectedRange = range)
        }
    }

    fun getStartDateOneYearAgo(): String {
        return LocalDate.now().minusYears(1).toString()
    }

}
