package com.pradeep.currencyconverter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ─────────────────────────────────────────────
// Font family
// ─────────────────────────────────────────────
// Wise uses "Inter" (very close to the system sans-serif on Android).
// Add Inter TTF files to res/font/ and reference them here.
// Falls back to system default (Roboto) if not present.
//
// To add Inter:
//   1. Download from https://fonts.google.com/specimen/Inter
//   2. Place inter_regular.ttf, inter_medium.ttf, inter_semibold.ttf,
//      inter_bold.ttf in app/src/main/res/font/
//   3. Uncomment the FontFamily block below and remove the fallback line.
//
// val InterFontFamily = FontFamily(
//     Font(R.font.inter_regular,  FontWeight.Normal),
//     Font(R.font.inter_medium,   FontWeight.Medium),
//     Font(R.font.inter_semibold, FontWeight.SemiBold),
//     Font(R.font.inter_bold,     FontWeight.Bold),
// )

val InterFontFamily = FontFamily.Default   // replace with above when fonts are added

// ─────────────────────────────────────────────
// Typography scale  (mapped from design px → sp)
// Design canvas is 375 wide (1×); sp ≈ px at default scale.
// ─────────────────────────────────────────────

val CurrencyTypography = Typography(

    // ── Display ──────────────────────────────
    // Not heavily used; kept for completeness.
    displayLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize   = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
    ),
    displayMedium = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize   = 45.sp,
        lineHeight = 52.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize   = 36.sp,
        lineHeight = 44.sp,
    ),

    // ── Headline ─────────────────────────────
    // headlineLarge  → app logo / hero text (28 px in design)
    headlineLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize   = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = (-0.5).sp,
    ),
    // headlineMedium → large amount field (22 px in design)
    headlineMedium = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize   = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.25).sp,
    ),
    // headlineSmall  → section heading inside cards (18 px)
    headlineSmall = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize   = 18.sp,
        lineHeight = 24.sp,
    ),

    // ── Title ────────────────────────────────
    // titleLarge  → screen / card title (16 px, semi-bold)
    titleLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize   = 16.sp,
        lineHeight = 22.sp,
    ),
    // titleMedium → currency name in converter row (15 px)
    titleMedium = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize   = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),
    // titleSmall  → tab labels (13 px, medium)
    titleSmall = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize   = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.1.sp,
    ),

    // ── Body ─────────────────────────────────
    // bodyLarge   → primary body text, list row primary (14 px)
    bodyLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize   = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
    ),
    // bodyMedium  → secondary body / row sub-label (13 px)
    bodyMedium = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize   = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.25.sp,
    ),
    // bodySmall   → muted helper text, rate sub-label (11 px)
    bodySmall = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize   = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
    ),

    // ── Label ────────────────────────────────
    // labelLarge  → button text (14 px, medium) — also used by M3 buttons automatically
    labelLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize   = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),
    // labelMedium → badge / chip text (12 px)
    labelMedium = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize   = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),
    // labelSmall  → chart axis labels, timestamps (10 px)
    labelSmall = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize   = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.5.sp,
    ),
)

// ─────────────────────────────────────────────
// Convenience aliases (optional — reference
// these in composables instead of raw sp values)
// ─────────────────────────────────────────────

/** App logo / hero display text */
val Typography.logoStyle get() = headlineLarge

/** Large amount input field */
val Typography.amountStyle get() = headlineMedium

/** Currency code label (EUR, USD …) */
val Typography.currencyCodeStyle get() = titleMedium

/** Chart x-axis tick label */
val Typography.chartLabelStyle get() = labelSmall

/** Settings row subtitle / helper text */
val Typography.helperStyle get() = bodySmall
