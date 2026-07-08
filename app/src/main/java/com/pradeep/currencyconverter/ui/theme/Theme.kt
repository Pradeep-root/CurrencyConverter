package com.pradeep.currencyconverter.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// ─────────────────────────────────────────────
// Extended (non-Material3) colour tokens
// ─────────────────────────────────────────────

@Immutable
data class ExtendedColors(
    /** Rate trending up */
    val positive: Color,
    val positiveContainer: Color,
    /** Rate trending down */
    val negative: Color,
    val negativeContainer: Color,
    /** Secondary label text (e.g. currency name under amount) */
    val textSecondary: Color,
    /** Muted / placeholder text */
    val textMuted: Color,
    /** Card / row divider */
    val divider: Color,
    /** Toggle ON thumb colour — same as primary, surfaced here for convenience */
    val toggleOn: Color,
    /** Toggle OFF track */
    val toggleOff: Color,
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        positive          = PositiveText,
        positiveContainer = PositiveContainer,
        negative          = NegativeText,
        negativeContainer = NegativeContainer,
        textSecondary     = TextSecondaryLight,
        textMuted         = TextMutedLight,
        divider           = DividerLight,
        toggleOn          = WiseLimeGreen,
        toggleOff         = ToggleOff,
    )
}

// ─────────────────────────────────────────────
// Material 3 colour schemes
// ─────────────────────────────────────────────

private val LightColorScheme: ColorScheme = lightColorScheme(
    // ── Primary ──────────────────────────────
    primary              = WiseLimeGreen,
    onPrimary            = WiseDarkGreen,
    primaryContainer     = WiseMidGreen,
    onPrimaryContainer   = WiseSoftLime,

    // ── Secondary (neutral-green tones) ──────
    secondary            = Color(0xFF506645),
    onSecondary          = Color(0xFFFFFFFF),
    secondaryContainer   = Color(0xFFD3EBC3),
    onSecondaryContainer = Color(0xFF0E1F08),

    // ── Tertiary (accent blue for badges) ────
    tertiary             = Color(0xFF185FA5),
    onTertiary           = Color(0xFFFFFFFF),
    tertiaryContainer    = Color(0xFFE6F1FB),
    onTertiaryContainer  = Color(0xFF001D39),

    // ── Error ────────────────────────────────
    error                = NegativeText,
    onError              = Color(0xFFFFFFFF),
    errorContainer       = NegativeContainer,
    onErrorContainer     = Color(0xFF410002),

    // ── Background ───────────────────────────
    background           = BackgroundLight,
    onBackground         = TextPrimaryLight,

    // ── Surface ──────────────────────────────
    surface              = SurfaceLight,
    onSurface            = TextPrimaryLight,
    surfaceVariant       = SurfaceVariantLight,
    onSurfaceVariant     = TextSecondaryLight,

    // ── Outline ──────────────────────────────
    outline              = OutlineLight,
    outlineVariant       = DividerLight,

    // ── Inverse ──────────────────────────────
    inverseSurface       = WiseDarkGreen,
    inverseOnSurface     = BackgroundLight,
    inversePrimary       = WiseLimeGreen,

    // ── Scrim ────────────────────────────────
    scrim                = Color(0xFF000000),
)

private val DarkColorScheme: ColorScheme = darkColorScheme(
    // ── Primary ──────────────────────────────
    primary              = WiseLimeGreen,
    onPrimary            = WiseDarkGreen,
    primaryContainer     = WiseMidGreen,
    onPrimaryContainer   = WiseSoftLime,

    // ── Secondary ────────────────────────────
    secondary            = Color(0xFFB7CCA9),
    onSecondary          = Color(0xFF223519),
    secondaryContainer   = Color(0xFF384D2E),
    onSecondaryContainer = Color(0xFFD3EBC3),

    // ── Tertiary ─────────────────────────────
    tertiary             = Color(0xFFADC8FF),
    onTertiary           = Color(0xFF002F67),
    tertiaryContainer    = Color(0xFF0C447C),
    onTertiaryContainer  = Color(0xFFD6E3FF),

    // ── Error ────────────────────────────────
    error                = NegativeTextDark,
    onError              = Color(0xFF690005),
    errorContainer       = NegativeContainerDark,
    onErrorContainer     = Color(0xFFFFDAD6),

    // ── Background ───────────────────────────
    background           = BackgroundDark,
    onBackground         = TextPrimaryDark,

    // ── Surface ──────────────────────────────
    surface              = SurfaceDark,
    onSurface            = TextPrimaryDark,
    surfaceVariant       = SurfaceVariantDark,
    onSurfaceVariant     = TextSecondaryDark,

    // ── Outline ──────────────────────────────
    outline              = OutlineDark,
    outlineVariant       = DividerDark,

    // ── Inverse ──────────────────────────────
    inverseSurface       = BackgroundLight,
    inverseOnSurface     = WiseDarkGreen,
    inversePrimary       = WiseMidGreen,

    // ── Scrim ────────────────────────────────
    scrim                = Color(0xFF000000),
)

// ─────────────────────────────────────────────
// Theme entry point
// ─────────────────────────────────────────────

@Composable
fun CurrencyConverterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val extendedColors = if (darkTheme) {
        ExtendedColors(
            positive          = PositiveTextDark,
            positiveContainer = PositiveContainerDark,
            negative          = NegativeTextDark,
            negativeContainer = NegativeContainerDark,
            textSecondary     = TextSecondaryDark,
            textMuted         = TextMutedDark,
            divider           = DividerDark,
            toggleOn          = WiseLimeGreen,
            toggleOff         = ToggleOffDark,
        )
    } else {
        ExtendedColors(
            positive          = PositiveText,
            positiveContainer = PositiveContainer,
            negative          = NegativeText,
            negativeContainer = NegativeContainer,
            textSecondary     = TextSecondaryLight,
            textMuted         = TextMutedLight,
            divider           = DividerLight,
            toggleOn          = WiseLimeGreen,
            toggleOff         = ToggleOff,
        )
    }

    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography  = CurrencyTypography,
            content     = content,
        )
    }
}

/**
 * Shortcut to access extended colours from any composable inside [CurrencyConverterTheme].
 *
 * Usage:
 * ```kotlin
 * val ext = MaterialTheme.extendedColors
 * Text(color = ext.positive, ...)
 * ```
 */
val MaterialTheme.extendedColors: ExtendedColors
    @Composable get() = LocalExtendedColors.current
