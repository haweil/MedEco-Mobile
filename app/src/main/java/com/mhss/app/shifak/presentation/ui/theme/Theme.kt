package com.mhss.app.shifak.presentation.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorPalette = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    surface = DarkSurfaceColor,
    background = DarkBackgroundColor,
    surfaceTint = Color.Transparent,
    surfaceVariant = DarkSurfaceColor,
    surfaceContainerHighest = DarkSurfaceColor,
    surfaceContainerLow = DarkSurfaceColor,
    surfaceContainerLowest = DarkSurfaceColor,
    surfaceContainer = DarkSurfaceColor,
    surfaceContainerHigh = DarkSurfaceColor,
    surfaceDim = DarkSurfaceColor,
    surfaceBright = DarkSurfaceColor
)

private val LightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    surface = LightSurfaceColor,
    background = LightBackgroundColor,
    surfaceTint = Color.Transparent,
    surfaceVariant = LightSurfaceColor,
    surfaceContainerHighest = LightSurfaceColor,
    surfaceContainerLow = LightSurfaceColor,
    surfaceContainerLowest = LightSurfaceColor,
    surfaceContainer = LightSurfaceColor,
    surfaceContainerHigh = LightSurfaceColor,
    surfaceDim = LightSurfaceColor,
    surfaceBright = LightSurfaceColor
)

@Composable
fun MedEcoTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}