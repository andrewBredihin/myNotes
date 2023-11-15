package com.bav.mynotes.presenter.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class CustomThemeColors(
    val primary: Color,
    val secondary: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
)

data class CustomThemeTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val toolbar: TextStyle,
)

data class CustomThemeShape(
    val padding: Dp,
    val minSize: Dp,
    val cornerStyle: Shape,
)

object CustomTheme {
    val colors: CustomThemeColors
        @Composable
        get() = LocalCustomThemeColors.current

    val typography: CustomThemeTypography
        @Composable
        get() = LocalCustomThemeTypography.current

    val shapes: CustomThemeShape
        @Composable
        get() = LocalCustomThemeShape.current
}

enum class CustomThemeStyle {
    Primary,
}

enum class CustomThemeSize {
    Medium,
}

enum class CustomThemeCorners {
    Rounded, Flat
}

val LocalCustomThemeColors = staticCompositionLocalOf<CustomThemeColors> {
    error("No colors provided")
}

val LocalCustomThemeTypography = staticCompositionLocalOf<CustomThemeTypography> {
    error("No fonts provided")
}

val LocalCustomThemeShape = staticCompositionLocalOf<CustomThemeShape> {
    error("No shapes provided")
}
