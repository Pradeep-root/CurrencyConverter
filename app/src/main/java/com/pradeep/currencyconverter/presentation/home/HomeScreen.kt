package com.pradeep.currencyconverter.presentation.home

import LineAxisConfig
import LineChart
import LineChartStyle
import LineCrosshairConfig
import LineDataPoint
import LineDataSet
import LineSeries
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pradeep.currencyconverter.domain.model.ConverterData
import com.pradeep.currencyconverter.domain.model.CurrencyRate
import com.pradeep.currencyconverter.presentation.components.ConverterTile
import com.pradeep.currencyconverter.presentation.search.SearchScreen
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showSearchSheet by remember { mutableStateOf(false) }
    var editingCurrency by remember { mutableStateOf("base") }
    val sheetState = rememberModalBottomSheetState()

    when (val state = uiState) {
        is HomeUiState.Loading -> LoadingContent()
        is HomeUiState.Success -> {
            Box(modifier = Modifier.fillMaxSize()) {
                HomeContent(
                    converterData = state.data,
                    amount = state.amount,
                    historicalData = state.historicalData,
                    selectedRange = state.selectedRange,
                    onAmountChange = { amountString ->
                        viewModel.updateAmount(amountString)
                    },
                    onSwap = { viewModel.swapCurrencies() },
                    onTimeRangeSelected = { viewModel.updateTimeRange(it) },
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun HomeContent(
    converterData: ConverterData,
    amount: String,
    historicalData: List<CurrencyRate>,
    selectedRange: TimeRange,
    onAmountChange: (String) -> Unit,
    onSwap: () -> Unit,
    onTimeRangeSelected: (TimeRange) -> Unit,
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
        item {
            HistoricalRateChart(
                historicalData = historicalData,
                selectedRange = selectedRange,
                onTimeRangeSelected = onTimeRangeSelected,
                base = converterData.base,
                quote = converterData.quote
            )
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun HistoricalRateChart(
    historicalData: List<CurrencyRate>,
    selectedRange: TimeRange,
    onTimeRangeSelected: (TimeRange) -> Unit,
    base: String,
    quote: String
) {
    val dateFormatter = DateTimeFormatter.ofPattern("MMM d")
    var selectedPointIndex by remember { mutableStateOf<Int?>(null) }

    // Filter data based on selected range
    val filteredData = remember(historicalData, selectedRange) {
        val cutoffDate = LocalDate.now().minusDays(selectedRange.days.toLong())
        historicalData
            .filter { 
                try {
                    LocalDate.parse(it.date) >= cutoffDate
                } catch (e: Exception) {
                    false
                }
            }
            .sortedBy { it.date }
    }

    // Map to LineChart format
    val lineSeries = remember(filteredData) {
        val points = filteredData.mapIndexed { index, rate ->
            LineDataPoint(
                x = index.toFloat(),
                y = rate.rate.toFloat(),
                label = try {
                    LocalDate.parse(rate.date).format(dateFormatter)
                } catch (e: Exception) {
                    ""
                }
            )
        }

        if (points.isEmpty()) emptyList()

        else listOf(
            LineSeries(
                id = "${base}_${quote}",
                label = "$base to $quote",
                points = points,
                color = Color(0xFF163300),
                fillAlpha = 0.1f,
                strokeGradientColors = listOf(
                    Color(0xFF163300),
                    Color(0xFF163300)
                )
            )
        )
    }

    val dataSet = LineDataSet(series = lineSeries)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
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
                text = "Historical Rate ($base to $quote)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Time Range Selector
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(TimeRange.values()) { range ->
                    FilterChip(
                        selected = selectedRange == range,
                        onClick = { onTimeRangeSelected(range) },
                        label = { Text(range.label) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (lineSeries.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No data available for this range",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    LineChart(
                        dataSet = dataSet,
                        modifier = Modifier.fillMaxSize(),
                        style = LineChartStyle(curveType = LineCurveType.MonotoneCubic),
                        axisConfig = LineAxisConfig(
                            includeZeroInYRange = false,
                            yLabelFormatter = { "%.2f".format(it) },
                            yTickCount = 6,
                            maxXLabels = 6
                        ),
                        crosshairConfig = LineCrosshairConfig(
                            tooltipFormatter = { _, p -> 
                                "${p.label}\n${"%.2f".format(p.y)}" 
                            }
                        ),
                        selectedPointIndex = selectedPointIndex,
                        onPointSelected = { selectedPointIndex = it }
                    )
                }
            }
        }
    }
}
