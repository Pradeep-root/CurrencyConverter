package com.pradeep.currencyconverter.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.cos

// ─────────────────────────────────────────────────────────────
//  Design tokens
// ─────────────────────────────────────────────────────────────

private val WiseGreen     = Color(0xFF9FE870)
private val WiseGreenDark = Color(0xFF2DCC70)
private val WiseRed       = Color(0xFFFF6B6B)
private val Background    = Color(0xFFFFFFFF)
private val SurfaceCard   = Color(0xFFF6F8F4)
private val TextPrimary   = Color(0xFF1A1A1A)
private val TextSecondary = Color(0xFF717171)
private val TextTertiary  = Color(0xFFB0B0B0)
private val DividerColor  = Color(0xFFF0F0F0)

// ─────────────────────────────────────────────────────────────
//  Models
// ─────────────────────────────────────────────────────────────

data class RatePoint(val date: LocalDate, val rate: Double)

enum class Period(val label: String, val days: Int) {
    ONE_WEEK("1W", 7),
    ONE_MONTH("1M", 30),
    THREE_MONTHS("3M", 90),
    SIX_MONTHS("6M", 180),
    ONE_YEAR("1Y", 365),
}

// ─────────────────────────────────────────────────────────────
//  Sample data — guaranteed large visible swings
// ─────────────────────────────────────────────────────────────

@RequiresApi(Build.VERSION_CODES.O)
fun generateRates(days: Int): List<RatePoint> {
    val today = LocalDate.now()
    return (days downTo 0).mapIndexed { index, daysAgo ->
        val t     = index.toDouble() / days.coerceAtLeast(1)
        // Multiple sin waves so it looks like real FX data
        val rate = 1.0820 +
            sin(t * Math.PI * 6.0) * 0.030 +   // big primary wave
            cos(t * Math.PI * 14.0) * 0.010 +  // medium secondary wave
            sin(t * Math.PI * 30.0) * 0.004 +  // small noise
            t * 0.015                           // slight upward trend
        RatePoint(today.minusDays(daysAgo.toLong()), rate)
    }
}

// ─────────────────────────────────────────────────────────────
//  Root screen
// ─────────────────────────────────────────────────────────────

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WiseCurrencyScreen() {
    val ratesMap = remember { Period.values().associateWith { generateRates(it.days) } }
    var period   by remember { mutableStateOf(Period.ONE_MONTH) }
    var scrub    by remember(period) { mutableStateOf(-1) }

    val rates      = ratesMap[period]!!
    val latestRate = rates.last().rate
    val firstRate  = rates.first().rate
    val scrubRate  = if (scrub >= 0) rates[scrub].rate else latestRate
    val scrubDate  = if (scrub >= 0) rates[scrub].date else rates.last().date

    val change      = latestRate - firstRate
    val changePct   = if (firstRate != 0.0) (change / firstRate) * 100.0 else 0.0
    val isPositive  = change >= 0.0
    val accentColor = if (isPositive) WiseGreenDark else WiseRed

    Surface(modifier = Modifier.fillMaxSize(), color = Background) {
        Column(modifier = Modifier.fillMaxSize()) {

            // ── Top bar ─────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "1 EUR =",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextSecondary, fontSize = 13.sp
                        )
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = "%.4f USD".format(scrubRate),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            letterSpacing = (-0.5).sp
                        )
                    )
                }
                if (scrub < 0) {
                    Column(horizontalAlignment = Alignment.End) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(accentColor.copy(alpha = 0.10f))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "%s%.2f%%".format(if (isPositive) "+" else "", changePct),
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = accentColor,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp
                                )
                            )
                        }
                        Spacer(Modifier.height(3.dp))
                        Text(
                            text = "%s%.4f".format(if (isPositive) "+" else "", change),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextSecondary, fontSize = 12.sp
                            )
                        )
                    }
                } else {
                    Text(
                        text = scrubDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = TextSecondary, fontSize = 13.sp
                        )
                    )
                }
            }

            // ── Stat row ─────────────────────────────────────────
            if (scrub < 0) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    StatChip("Low",  "%.4f".format(rates.minOf { it.rate }))
                    StatChip("High", "%.4f".format(rates.maxOf { it.rate }))
                    StatChip("Avg",  "%.4f".format(rates.map { it.rate }.average()))
                }
            } else {
                Spacer(Modifier.height(38.dp))
            }

            // ── Chart ────────────────────────────────────────────
            WiseLineChart(
                dataPoints = rates,
                isPositive = isPositive,
                scrubIndex = scrub,
                onScrub    = { scrub = it },
                modifier   = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
            )

            Spacer(Modifier.height(4.dp))

            // ── Period tabs ──────────────────────────────────────
            WisePeriodTabs(
                selected = period,
                onSelect = { period = it; scrub = -1 }
            )

            Spacer(Modifier.height(24.dp))

            // ── Rate alert card ──────────────────────────────────
            RateAlertCard(currentRate = latestRate)
        }
    }
}

// ─────────────────────────────────────────────────────────────
//  Stat chip
// ─────────────────────────────────────────────────────────────

@Composable
fun StatChip(label: String, value: String) {
    Column {
        Text(
            label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = TextTertiary, fontSize = 11.sp
            )
        )
        Spacer(Modifier.height(1.dp))
        Text(
            value,
            style = MaterialTheme.typography.bodySmall.copy(
                color = TextPrimary, fontWeight = FontWeight.Medium, fontSize = 13.sp
            )
        )
    }
}

// ─────────────────────────────────────────────────────────────
//  Chart — THE FIX IS HERE
// ─────────────────────────────────────────────────────────────

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WiseLineChart(
    dataPoints: List<RatePoint>,
    isPositive: Boolean,
    scrubIndex: Int,
    onScrub:    (Int) -> Unit,
    modifier:   Modifier = Modifier
) {
    if (dataPoints.isEmpty()) return

    val lineColor = if (isPositive) WiseGreenDark else WiseRed
    val fillStart = if (isPositive) WiseGreen.copy(alpha = 0.35f) else WiseRed.copy(alpha = 0.25f)

    val progress = remember(dataPoints) { Animatable(0f) }
    LaunchedEffect(dataPoints) {
        progress.snapTo(0f)
        progress.animateTo(1f, tween(800, easing = EaseInOutCubic))
    }

    val haptic        = LocalHapticFeedback.current
    var lastHapticIdx by remember { mutableStateOf(-1) }
    val textMeasurer  = rememberTextMeasurer()

    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(dataPoints) {
                    detectHorizontalDragGestures(
                        onDragStart = { offset ->
                            val idx = toIdx(offset.x, size.width.toFloat(), dataPoints.size)
                            onScrub(idx)
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            lastHapticIdx = idx
                        },
                        onDragEnd    = { onScrub(-1); lastHapticIdx = -1 },
                        onDragCancel = { onScrub(-1); lastHapticIdx = -1 }
                    ) { change, _ ->
                        val idx = toIdx(change.position.x, size.width.toFloat(), dataPoints.size)
                        onScrub(idx)
                        if (idx != lastHapticIdx) {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            lastHapticIdx = idx
                        }
                    }
                }
                .pointerInput(dataPoints) {
                    detectTapGestures(onPress = { offset ->
                        val idx = toIdx(offset.x, size.width.toFloat(), dataPoints.size)
                        onScrub(idx)
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        tryAwaitRelease()
                        onScrub(-1)
                    })
                }
        ) {
            val w      = size.width
            val h      = size.height
            val xLabH  = 20.dp.toPx()
            val chartH = h - xLabH

            // ── THE CRITICAL FIX ──────────────────────────────────
            // Never let the visible range collapse. Add explicit top/bottom
            // padding in pixel space so the line always uses ~80% of height.
            val rawMin = dataPoints.minOf { it.rate }
            val rawMax = dataPoints.maxOf { it.rate }
            val rawRange = rawMax - rawMin

            // Force at least 0.5% of mid-value as visible range
            val midValue   = (rawMin + rawMax) / 2.0
            val forcedRange = maxOf(rawRange, midValue * 0.005)

            // Add 10% headroom above and below
            val extraPad = forcedRange * 0.10
            val visMin   = rawMin - extraPad
            val visMax   = rawMax + extraPad
            val visRange = visMax - visMin   // always > 0

            // Map rate → Y pixel (top = high rate, bottom = low rate)
            fun yOf(rate: Double): Float {
                val fraction = ((rate - visMin) / visRange).coerceIn(0.0, 1.0)
                return (chartH * (1.0 - fraction)).toFloat()
            }

            fun xOf(i: Int): Float =
                if (dataPoints.size <= 1) w / 2f
                else i.toFloat() / (dataPoints.size - 1) * w

            // ── Build line path ───────────────────────────────────
            val linePath = Path().apply {
                dataPoints.forEachIndexed { i, p ->
                    val x = xOf(i)
                    val y = yOf(p.rate)
                    if (i == 0) moveTo(x, y) else lineTo(x, y)
                }
            }

            // ── Animated reveal clip ──────────────────────────────
            val revealClip = Path().apply {
                addRect(Rect(0f, 0f, w * progress.value, h))
            }

            // ── Fill path (line + close to bottom) ───────────────
            val fillPath = Path().apply {
                addPath(linePath)
                lineTo(xOf(dataPoints.lastIndex), chartH)
                lineTo(0f, chartH)
                close()
            }

            clipPath(revealClip) {
                // Gradient fill under line
                drawPath(
                    fillPath,
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to fillStart,
                            1.0f to Color.Transparent
                        ),
                        startY = 0f,
                        endY   = chartH
                    )
                )
                // The line
                drawPath(
                    linePath,
                    color = lineColor,
                    style = Stroke(
                        width = 2.5.dp.toPx(),
                        cap   = StrokeCap.Round,
                        join  = StrokeJoin.Round
                    )
                )
            }

            // ── X-axis date labels ────────────────────────────────
            val fmt   = DateTimeFormatter.ofPattern("d MMM")
            val count = if (dataPoints.size <= 7) dataPoints.size else 5
            val step  = (dataPoints.size - 1).toFloat() / (count - 1).coerceAtLeast(1)
            (0 until count).forEach { n ->
                val i        = (n * step).roundToInt().coerceIn(0, dataPoints.lastIndex)
                val label    = dataPoints[i].date.format(fmt)
                val measured = textMeasurer.measure(
                    AnnotatedString(label),
                    style = TextStyle(fontSize = 10.sp, color = TextTertiary)
                )
                val lx = (xOf(i) - measured.size.width / 2f)
                    .coerceIn(0f, w - measured.size.width)
                drawText(measured, topLeft = Offset(lx, chartH + 4.dp.toPx()))
            }

            // ── Scrubber ──────────────────────────────────────────
            if (scrubIndex >= 0) {
                val idx = scrubIndex.coerceIn(0, dataPoints.lastIndex)
                val sx  = xOf(idx)
                val sy  = yOf(dataPoints[idx].rate)

                // Vertical rule
                drawLine(
                    color       = TextTertiary.copy(alpha = 0.4f),
                    start       = Offset(sx, 0f),
                    end         = Offset(sx, chartH),
                    strokeWidth = 1.dp.toPx()
                )
                // Outer glow
                drawCircle(
                    color  = lineColor.copy(alpha = 0.20f),
                    radius = 12.dp.toPx(),
                    center = Offset(sx, sy)
                )
                // White core
                drawCircle(Color.White, radius = 5.5.dp.toPx(), center = Offset(sx, sy))
                // Colored ring
                drawCircle(
                    lineColor,
                    radius = 5.5.dp.toPx(),
                    center = Offset(sx, sy),
                    style  = Stroke(2.2.dp.toPx())
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────
//  Period tabs
// ─────────────────────────────────────────────────────────────

@Composable
fun WisePeriodTabs(selected: Period, onSelect: (Period) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Period.values().forEach { p ->
            val isSelected = p == selected
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextButton(
                    onClick = { onSelect(p) },
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = if (isSelected) WiseGreen.copy(alpha = 0.15f)
                                         else Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        p.label,
                        style = MaterialTheme.typography.labelLarge.copy(
                            color      = if (isSelected) TextPrimary else TextTertiary,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            fontSize   = 13.sp
                        )
                    )
                }
                if (isSelected) {
                    Box(
                        Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(WiseGreenDark)
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────
//  Rate alert card
// ─────────────────────────────────────────────────────────────

@Composable
fun RateAlertCard(currentRate: Double) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        HorizontalDivider(color = DividerColor, thickness = 1.dp)
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SurfaceCard)
                .padding(16.dp),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "Set a rate alert",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color      = TextPrimary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize   = 14.sp
                    )
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    "Notify me when rate hits %.4f".format(currentRate * 1.005),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color    = TextSecondary,
                        fontSize = 12.sp
                    )
                )
            }
            Spacer(Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(WiseGreenDark)
                    .padding(horizontal = 14.dp, vertical = 9.dp)
            ) {
                Text(
                    "Set",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color      = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize   = 13.sp
                    )
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────
//  Helper
// ─────────────────────────────────────────────────────────────

private fun toIdx(x: Float, width: Float, size: Int): Int =
    ((x / width) * (size - 1)).roundToInt().coerceIn(0, size - 1)

// ─────────────────────────────────────────────────────────────
//  Preview
// ─────────────────────────────────────────────────────────────

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, widthDp = 390, heightDp = 780)
@Composable
fun WiseCurrencyScreenPreview() {
    MaterialTheme { WiseCurrencyScreen() }
}
