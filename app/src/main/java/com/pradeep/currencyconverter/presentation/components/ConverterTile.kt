package com.pradeep.currencyconverter.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pradeep.currencyconverter.core.common.CurrencyFlags
import com.pradeep.currencyconverter.domain.model.ConverterData

@Composable
fun ConverterTile(
    converterData: ConverterData
) {

    var topValue by remember { mutableStateOf(converterData.base) }
    var bottomValue by remember { mutableStateOf(converterData.quote) }

    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth()
            .padding(16.dp)
    ) {


        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Avatar(
                        modifier = Modifier.size(45.dp),
                        logoUrl = CurrencyFlags.getFlagUrl(topValue)
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Avatar(
                        modifier = Modifier.size(45.dp),
                        logoUrl = CurrencyFlags.getFlagUrl(bottomValue)
                    )
                }
            }
        }


        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.Center)
                .size(30.dp)
                .graphicsLayer {}
                .background(MaterialTheme.colorScheme.primary, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.SwapVert,
                contentDescription = "Swap",
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ConverterTilePreview() {
    ConverterTile(
        ConverterData(
            date = "2026-07-11",
            base = "EUR",
            quote = "INR",
            rate = 1.1441,
            total = 124.00
        )
    )
}

