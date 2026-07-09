package com.pradeep.currencyconverter.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pradeep.currencyconverter.ui.theme.CurrencyTypography
import com.pradeep.currencyconverter.ui.theme.extendedColors

@Composable
fun BaseCurrencyTile(
    currencyText: String
) {

    OutlinedButton(
        onClick = {},
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Text(text = currencyText, style = CurrencyTypography.labelLarge, color = MaterialTheme.extendedColors.textMuted)
    }
}

@Preview
@Composable
fun BaseCurrencyTilePreview() {
    BaseCurrencyTile("EUR")
}