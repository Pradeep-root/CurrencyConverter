package com.pradeep.currencyconverter.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pradeep.currencyconverter.core.common.CurrencyFlags
import com.pradeep.currencyconverter.domain.model.ConverterData
import kotlinx.coroutines.launch

@Composable
fun ConverterTile(
    converterData: ConverterData,
    amount: String,
    onAmountChange: (String) -> Unit,
    onSwap: () -> Unit,
    onBaseClick: () -> Unit,
    onQuoteClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val rotation = remember { Animatable(0f) }
    val swapProgress = remember { Animatable(0f) }
    var cardHeightPx by remember { mutableStateOf(0f) }
    val spacingPx = with(LocalDensity.current) { 12.dp.toPx() }

    fun triggerSwap() {
        if (rotation.isRunning || swapProgress.isRunning) return // avoid double-tap glitches
        scope.launch {
            launch {
                rotation.animateTo(
                    targetValue = rotation.value + 180f,
                    animationSpec = tween(350, easing = FastOutSlowInEasing)
                )
            }
            swapProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(350, easing = FastOutSlowInEasing)
            )

            onSwap()

            swapProgress.snapTo(0f)
        }
    }

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
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        cardHeightPx = coordinates.size.height.toFloat()
                    }
                    .graphicsLayer {
                        translationY = swapProgress.value * (cardHeightPx + spacingPx)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { onBaseClick() }
                    ) {
                        Avatar(
                            modifier = Modifier.size(45.dp),
                            logoUrl = CurrencyFlags.getFlagUrl(converterData.base)
                        )
                        Text(
                            text = converterData.base,
                            modifier = Modifier.padding(start = 8.dp),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    BasicTextField(
                        value = amount,
                        onValueChange = { onAmountChange(it) },
                        textStyle = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier.padding(horizontal = 4.dp),
                        singleLine = true
                    )
                }
            }

            // BOTTOM CARD (quote currency)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        translationY = -swapProgress.value * (cardHeightPx + spacingPx)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { onQuoteClick() }
                    ) {
                        Avatar(
                            modifier = Modifier.size(45.dp),
                            logoUrl = CurrencyFlags.getFlagUrl(converterData.quote)
                        )
                        Text(
                            text = converterData.quote,
                            modifier = Modifier.padding(start = 8.dp),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    BasicTextField(
                        value = "${converterData.total}",
                        onValueChange = { },
                        textStyle = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier.padding(horizontal = 4.dp),
                        singleLine = true
                    )
                }
            }
        }

        IconButton(
            onClick = { triggerSwap() },
            modifier = Modifier
                .align(Alignment.Center)
                .size(30.dp)
                .graphicsLayer { rotationZ = rotation.value }
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
        converterData = ConverterData(
            date = "2026-07-11",
            base = "EUR",
            quote = "INR",
            rate = 1.1441,
            total = 124.00
        ),
        amount = "1.0",
        onAmountChange = {},
        onSwap = {},
        onBaseClick = {},
        onQuoteClick = {}
    )
}

