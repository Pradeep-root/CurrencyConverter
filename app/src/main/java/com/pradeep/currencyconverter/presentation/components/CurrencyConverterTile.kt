package com.pradeep.currencyconverter.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pradeep.currencyconverter.domain.model.CalculatorData
import com.pradeep.currencyconverter.ui.theme.md_theme_light_primary

@Composable
fun CurrencyConverterTile(
    modifier: Modifier = Modifier,
    calculatorData: CalculatorData
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "BASE",
                style = MaterialTheme.typography.labelMedium
            )
            CurrencyEditField(calculatorData)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = Color.Gray
                )

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = md_theme_light_primary),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.SwapVert,
                        contentDescription = "Swap currencies"
                    )
                }
            }
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "QUOTE",
                style = MaterialTheme.typography.labelMedium
            )
            CurrencyEditField(calculatorData)

        }

    }

}


@Preview
@Composable
fun CurrencyConverterTilePreview() {
    CurrencyConverterTile(
        calculatorData = CalculatorData(
            logoUrl = "https://flagcdn.com/eu.svg",
            symbol = "EUR",
            rate = "1.23",
            total = "1.23"
        )
    )
}