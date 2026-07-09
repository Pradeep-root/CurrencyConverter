package com.pradeep.currencyconverter.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pradeep.currencyconverter.domain.model.InputFieldData

@Composable
fun CurrencyEditField(
    inputFieldData: InputFieldData,
    onValueChange: (String) -> Unit
) {

    var inputText by remember(inputFieldData.total) {
        mutableStateOf(inputFieldData.total)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Avatar(logoUrl = inputFieldData.flagUrl)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = inputFieldData.symbol,
                        style = MaterialTheme.typography.titleLarge
                    )
                    BasicTextField(
                        value = inputText,
                        onValueChange = onValueChange,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = inputFieldData.symbol,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(text = inputFieldData.rate, style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyEditFieldPreview() {
    CurrencyEditField(
        inputFieldData = InputFieldData(
            flagUrl = "https://flagcdn.com/in.svg",
            symbol = "INR",
            rate = "1.23",
            total = "1.23"
        ),
        onValueChange = { }
    )
}