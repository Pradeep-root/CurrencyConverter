package com.pradeep.currencyconverter.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pradeep.currencyconverter.ui.theme.CurrencyTypography
import com.pradeep.currencyconverter.ui.theme.TextMutedDark


@Composable
fun BaseCurrencyTile(
    currencyText: String
) {

    OutlinedButton(
        onClick = {},
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
    ) {
        Text(text = currencyText, style = CurrencyTypography.labelLarge, color = TextMutedDark)
    }
}


@Preview
@Composable
fun BaseCurrencyTilePreview() {
    BaseCurrencyTile("EUR")
}