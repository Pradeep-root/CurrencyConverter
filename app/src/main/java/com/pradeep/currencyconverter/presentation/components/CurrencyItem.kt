package com.pradeep.currencyconverter.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pradeep.currencyconverter.core.common.CurrencyFlags
import com.pradeep.currencyconverter.domain.model.CurrencyRate

@Composable
fun CurrencyItem(
    modifier: Modifier = Modifier,
    currency: CurrencyRate,
    isBase: Boolean
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Avatar(
                modifier = modifier.size(45.dp),
                logoUrl = CurrencyFlags.getFlagUrl(if (isBase) currency.base else currency.quote)
                    ?: "EUR"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "${currency.base} - ${currency.quote}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "${currency.rate}",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CurrencyItemPreview() {
    CurrencyItem(
        currency = CurrencyRate(
            date = "2026-06-29",
            base = "EUR",
            quote = "BSD",
            rate = 1.1551
        ),
        isBase = true
    )
}