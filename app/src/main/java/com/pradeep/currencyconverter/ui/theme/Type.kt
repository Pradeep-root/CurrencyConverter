package com.pradeep.currencyconverter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ── Optional: add a custom font ────────────────────────────
// 1. Add font files to res/font/ (e.g. inter_regular.ttf)
// 2. Uncomment below and replace FontFamily.Default
//
// val InterFamily = FontFamily(
//     Font(R.font.inter_regular, FontWeight.Normal),
//     Font(R.font.inter_medium, FontWeight.Medium),
//     Font(R.font.inter_semibold, FontWeight.SemiBold),
//     Font(R.font.inter_bold, FontWeight.Bold),
//     Font(R.font.inter_extrabold, FontWeight.ExtraBold),
// )

val AppFont = FontFamily.Default // replace with InterFamily when font added

// ── Type scale ──────────────────────────────────────────────

val RateXTypography = Typography(

    // ─ Display (rarely used in app; keep for onboarding/splash) ─
    displayLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Black,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),

    // ─ Headline ──────────────────────────────────────────────
    headlineLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.5).sp
    ),
    headlineMedium = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    // ─ Title ─────────────────────────────────────────────────
    titleLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    // ─ Body ──────────────────────────────────────────────────
    bodyLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.04.sp
    ),

    // ─ Label ─────────────────────────────────────────────────
    labelLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.02.sp
    ),
    labelMedium = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.03.sp
    ),
    labelSmall = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.07.sp
    ),
)

// ── Amount input style (not in M3 scale — use directly) ────
// Use this for the large currency-amount input field on the converter screen.

val ConverterAmountStyle = TextStyle(
    fontFamily = AppFont,
    fontWeight = FontWeight.Bold,
    fontSize = 32.sp,
    lineHeight = 40.sp,
    letterSpacing = 0.sp
)