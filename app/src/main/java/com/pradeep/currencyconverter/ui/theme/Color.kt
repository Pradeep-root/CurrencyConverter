package com.pradeep.currencyconverter.ui.theme

import androidx.compose.ui.graphics.Color

// ── Brand palette ──────────────────────────────────────────

/** Deep navy — app bar, status bar, header gradients */
val BrandDeep = Color(0xFF1A1F71)

/** Mid-brand blue — gradient end colour, secondary surfaces */
val BrandMid = Color(0xFF1E3A8A)

/** Primary accent — buttons, active state, links */
val Accent = Color(0xFF2563EB)

/** Accent tint — selected row bg, chip fill, focus rings */
val AccentLight = Color(0xFFEFF6FF)

/** Focus ring / border highlight */
val AccentBorderFocus = Color(0xFF93C5FD)

// ── Surface / background ───────────────────────────────────

/** App-level page background */
val SurfaceApp = Color(0xFFF0F4FF)

/** Card / sheet surface */
val SurfaceCard = Color(0xFFFFFFFF)

/** Dividers, input borders */
val BorderDefault = Color(0xFFE2E8F0)

// ── Semantic colours ───────────────────────────────────────

/** Rate up / positive change */
val SuccessGreen = Color(0xFF059669)
val SuccessBg = Color(0xFFECFDF5)

/** Rate down / negative change */
val DangerRed = Color(0xFFDC2626)
val DangerBg = Color(0xFFFEF2F2)

/** Premium badge, AdSense labels, rate alerts */
val Gold = Color(0xFFF59E0B)

// ── Text colours ────────────────────────────────────────────

val TextPrimary = Color(0xFF0F172A)
val TextSecondary = Color(0xFF64748B)
val TextMuted = Color(0xFF94A3B8)

// ── M3 light colour scheme aliases ──────────────────────────
// These map RateX tokens to the 29 Material 3 colour roles.
// Reference: m3.material.io/styles/color/roles

val md_theme_light_primary = Accent
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = AccentLight
val md_theme_light_onPrimaryContainer = BrandDeep

val md_theme_light_secondary = BrandMid
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFD6E4FF)
val md_theme_light_onSecondaryContainer = Color(0xFF001B63)

val md_theme_light_tertiary = Gold
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFFEF3C7)
val md_theme_light_onTertiaryContainer = Color(0xFF4D3000)

val md_theme_light_error = DangerRed
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_errorContainer = DangerBg
val md_theme_light_onErrorContainer = Color(0xFF7A0019)

val md_theme_light_background = SurfaceApp
val md_theme_light_onBackground = TextPrimary

val md_theme_light_surface = SurfaceCard
val md_theme_light_onSurface = TextPrimary
val md_theme_light_surfaceVariant = Color(0xFFE8EDF8)
val md_theme_light_onSurfaceVariant = TextSecondary

val md_theme_light_outline = BorderDefault
val md_theme_light_outlineVariant = AccentBorderFocus
val md_theme_light_scrim = Color(0xFF000000)

val md_theme_light_inverseSurface = BrandDeep
val md_theme_light_inverseOnSurface = AccentLight
val md_theme_light_inversePrimary = Color(0xFF93C5FD)

val md_theme_light_surfaceTint = Accent