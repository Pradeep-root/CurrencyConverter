package com.pradeep.currencyconverter.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pradeep.currencyconverter.domain.model.CalculatorData
import com.pradeep.currencyconverter.presentation.components.CurrencyEditField

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel()
) {

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { Spacer(modifier = Modifier.height(4.dp)) }
        item {
            CurrencyEditField(
                CalculatorData(
                    logoUrl = "https://flagcdn.com/eu.svg",
                    symbol = "EUR",
                    rate = "1.23",
                    total = "1.23"
                )
            )
        }
    }

}

@Composable
fun ConverterCard() {

}