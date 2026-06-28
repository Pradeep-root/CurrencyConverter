package com.pradeep.currencyconverter.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pradeep.currencyconverter.data.local.CurrencyFlags
import com.pradeep.currencyconverter.domain.model.CalculatorData
import com.pradeep.currencyconverter.domain.model.CurrencyRate
import com.pradeep.currencyconverter.domain.model.InputFieldData
import com.pradeep.currencyconverter.presentation.components.CurrencyConverterTile

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, viewModel: HomeScreenViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is HomeUiState.Loading -> LoadingContent()
        is HomeUiState.Success -> {
            HomeContent(state.data, modifier)
        }

        is HomeUiState.Error -> ErrorContent(
            message = state.message, onRetry = viewModel::fetchCurrencyRates
        )
    }


}

@Composable
private fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorContent(message: String, onRetry: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = message, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onRetry) { Text("Retry") }
        }
    }
}

@Composable
private fun HomeContent(data: List<CurrencyRate>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { Spacer(modifier = Modifier.height(4.dp)) }
        val selectedCurrency = data.find { it.quote == "INR" }?.let { currency ->
            CalculatorData(
                baseInputFieldData = InputFieldData(
                    flagUrl = CurrencyFlags.getFlagUrl(currency.base) ?: "",
                    symbol = currency.base,
                    rate = currency.rate.toString(),
                    total = "1.23"
                ),
                quoteInputFieldData = InputFieldData(
                    flagUrl = CurrencyFlags.getFlagUrl(currency.quote) ?: "",
                    symbol = currency.quote,
                    rate = currency.rate.toString(),
                    total = "1.23"
                )
            )
        }
        selectedCurrency?.let {
            item {
                CurrencyConverterTile(calculatorData = selectedCurrency)
            }
        }
    }
}
