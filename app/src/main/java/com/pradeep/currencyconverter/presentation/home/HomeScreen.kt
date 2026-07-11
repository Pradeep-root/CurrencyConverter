package com.pradeep.currencyconverter.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pradeep.currencyconverter.domain.model.ConverterData
import com.pradeep.currencyconverter.presentation.components.ConverterTile
import com.pradeep.currencyconverter.presentation.search.SearchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showSearchSheet by remember { mutableStateOf(false) }
    var editingCurrency by remember { mutableStateOf("base") } // "base" or "quote"
    val sheetState = rememberModalBottomSheetState()

    when (val state = uiState) {
        is HomeUiState.Loading -> LoadingContent()
        is HomeUiState.Success -> {
            Box(modifier = Modifier.fillMaxSize()) {
                HomeContent(
                    converterData = state.data,
                    amount = state.amount,
                    onAmountChange = { amountString ->
                        val amount = amountString.toDoubleOrNull() ?: 0.0
                        viewModel.updateAmount(amount)
                    },
                    onSwap = { viewModel.swapCurrencies() },
                    onBaseClick = {
                        editingCurrency = "base"
                        showSearchSheet = true
                    },
                    onQuoteClick = {
                        editingCurrency = "quote"
                        showSearchSheet = true
                    }
                )

                if (showSearchSheet) {
                    ModalBottomSheet(
                        onDismissRequest = { showSearchSheet = false },
                        sheetState = sheetState,
                        containerColor = MaterialTheme.colorScheme.background
                    ) {
                        SearchScreen(
                            onCurrencySelected = { currency ->
                                if (editingCurrency == "base") {
                                    viewModel.updateBase(currency)
                                } else {
                                    viewModel.updateQuote(currency)
                                }
                                showSearchSheet = false
                            },
                            modifier = Modifier.padding(bottom = 32.dp)
                        )
                    }
                }
            }
        }

        is HomeUiState.Error -> ErrorContent(
            message = state.message, onRetry = { viewModel.fetchRate() }
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
private fun HomeContent(
    converterData: ConverterData,
    amount: String,
    onAmountChange: (String) -> Unit,
    onSwap: () -> Unit,
    onBaseClick: () -> Unit,
    onQuoteClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
            ConverterTile(
                converterData = converterData,
                amount = amount,
                onAmountChange = onAmountChange,
                onSwap = onSwap,
                onBaseClick = onBaseClick,
                onQuoteClick = onQuoteClick
            )
        }
        item {
            Spacer(modifier = Modifier.height(4.dp))
            CurrentRateCard(converterData)
        }
    }
}

@Composable
private fun CurrentRateCard(converterData: ConverterData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Mid-market rate",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "1 ${converterData.base} = ${converterData.rate} ${converterData.quote}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
