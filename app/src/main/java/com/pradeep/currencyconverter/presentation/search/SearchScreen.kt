package com.pradeep.currencyconverter.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import com.pradeep.currencyconverter.domain.model.CurrencyRate
import com.pradeep.currencyconverter.presentation.components.BaseCurrencyTile
import com.pradeep.currencyconverter.presentation.components.CurrencyItem
import com.pradeep.currencyconverter.ui.theme.extendedColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onCurrencySelected: (String) -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()
    var selectedBase by remember { mutableStateOf("EUR") }

    LaunchedEffect(Unit) {
        viewModel.fetchCurrencyRates(selectedBase)
    }

    when (val state = uiState) {
        is SearchUiState.Loading -> {
            LoadingContent()
        }

        is SearchUiState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                SearchField(query = query, onQueryChange = viewModel::onQueryChange)
                Spacer(modifier = Modifier.height(16.dp))
                BaseCurrencyBar(
                    baseCurrencies = baseCurrencyList(),
                    selectedBase = selectedBase,
                    onBaseSelected = { newBase ->
                        selectedBase = newBase
                        viewModel.fetchCurrencyRates(newBase)
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                CurrencyRateList(
                    currencyRates = state.data.filter { currencyRate ->
                        currencyRate.quote.contains(
                            viewModel.query.value,
                            ignoreCase = true
                        )
                    },
                    onCurrencySelected = onCurrencySelected
                )
            }
        }

        is SearchUiState.Error -> ErrorContent(
            message = state.message, onRetry = {viewModel.fetchCurrencyRates(selectedBase)}
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
fun BaseCurrencyBar(
    baseCurrencies: List<String>,
    selectedBase: String,
    onBaseSelected: (String) -> Unit
) {

    Column {
        Text(
            text = "BASE CURRENCY",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.extendedColors.textMuted
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(baseCurrencies) { currency ->
                BaseCurrencyTile(
                    currencyText = currency,
                    isSelected = (currency == selectedBase),
                    onClick = { onBaseSelected(currency) }
                )
            }
        }
    }
}

@Composable
fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        placeholder = { Text("Search") },
        shape = RoundedCornerShape(16.dp),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(Icons.Default.Close, contentDescription = "Clear")
                }
            }
        }
    )
}

@Composable
fun CurrencyRateList(
    currencyRates: List<CurrencyRate>,
    onCurrencySelected: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "CURRENCIES",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.extendedColors.textMuted
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(currencyRates) { item ->
                CurrencyItem(
                    currency = item,
                    isBase = false,
                    onClick = { onCurrencySelected(item.quote) }
                )
            }
        }
    }
}

private fun baseCurrencyList(): List<String> {
    return listOf("EUR", "USD", "GBP", "CHF", "AUD")
}

@Preview
@Composable
fun SearchFieldPreview() {
    SearchField(query = "") { }
}

@Preview
@Composable
fun BaseCurrencyBarPreview() {
    BaseCurrencyBar(baseCurrencyList(), "EUR", {})
}

@Preview
@Composable
fun CurrencyRateListPreview() {
    //CurrencyRateList()
}