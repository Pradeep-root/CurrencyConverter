package com.pradeep.currencyconverter.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pradeep.currencyconverter.domain.model.CalculatorData
import com.pradeep.currencyconverter.ui.theme.AccentLight
import com.pradeep.currencyconverter.ui.theme.ConverterAmountStyle

@Composable
fun CurrencyEditField(
    calculatorData: CalculatorData
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = AccentLight)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Avatar(logoUrl = calculatorData.logoUrl)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = calculatorData.symbol, style = MaterialTheme.typography.titleLarge)
                Text(text = calculatorData.symbol, style = MaterialTheme.typography.labelSmall)
            }
            Spacer(modifier = Modifier.weight(1f))
            Column {
                Text(text = calculatorData.total, style = ConverterAmountStyle)
                Text(text = calculatorData.rate, style = MaterialTheme.typography.labelSmall)
            }
        }
    }

}

@Preview
@Composable
fun CurrencyEditFieldPreview() {
    CurrencyEditField(
        CalculatorData(
            logoUrl = "https://flagcdn.com/eu.svg", symbol = "EUR", rate = "1.23", total = "1.23"
        )
    )
}