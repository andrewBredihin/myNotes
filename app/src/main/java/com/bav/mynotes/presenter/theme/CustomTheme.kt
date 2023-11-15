package com.bav.mynotes.presenter.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainTheme(
    style: CustomThemeStyle = CustomThemeStyle.Primary,
    textSize: CustomThemeSize = CustomThemeSize.Medium,
    paddingSize: CustomThemeSize = CustomThemeSize.Medium,
    corners: CustomThemeCorners = CustomThemeCorners.Rounded,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = when (darkTheme) {
        true -> {
            when (style) {
                CustomThemeStyle.Primary -> baseDarkPalette
            }
        }

        false -> {
            when (style) {
                CustomThemeStyle.Primary -> baseLightPalette
            }
        }
    }

    val typography = CustomThemeTypography(
        heading = TextStyle(
            fontSize = when (textSize) {
                CustomThemeSize.Medium -> 18.sp
            },
            fontWeight = FontWeight.Bold,
            color = colors.primaryText,
        ),
        body = TextStyle(
            fontSize = when (textSize) {
                CustomThemeSize.Medium -> 16.sp
            },
            fontWeight = FontWeight.Medium,
            color = colors.primaryText,
        ),
        toolbar = TextStyle(
            fontSize = when (textSize) {
                CustomThemeSize.Medium -> 24.sp
            },
            fontWeight = FontWeight.Bold,
            color = colors.primaryText,
        ),
    )

    val shapes = CustomThemeShape(
        padding = when (paddingSize) {
            CustomThemeSize.Medium -> 10.dp
        },
        minSize = when (paddingSize) {
            CustomThemeSize.Medium -> 50.dp
        },
        cornerStyle = when (corners) {
            CustomThemeCorners.Flat -> RoundedCornerShape(0.dp)
            CustomThemeCorners.Rounded -> RoundedCornerShape(10.dp)
        },
    )

    CompositionLocalProvider(
        LocalCustomThemeColors provides colors,
        LocalCustomThemeTypography provides typography,
        LocalCustomThemeShape provides shapes,
        content = content,
    )
}
