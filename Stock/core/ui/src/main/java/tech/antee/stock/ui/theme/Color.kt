package tech.antee.stock.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Black = Color(0xFF000000)

val Green = Color(0xFF1DB954)
val BlueCornflower = Color(0x8010A7C8)
val GreenSpring = Color(0xFF0DFA96)

// Text Colors
val BlackShark = Color(0xFF2F3034)
val GrayMid = Color(0xFF5D5E64)

// System Colors
val BlueRoyal = Color(0xFF3F8AE0)
val RedApricot = Color(0xFFEE7871)
val GreenBreakerBay = Color(0xFF56A18B)

// Secondary Colors
val PurpleWildBlueYonder = Color(0xFF8585BA)
val WhiteHeather = Color(0xFFB2BACA)
val WhiteSnuff = Color(0xFFDBDBEA)
val WhiteCatskill = Color(0xFFF7F9FB)
val BlackSilverChalice = Color(0x59000000)
val BlackDove = Color(0x80000000)
val White = Color(0xFFFFFFFF)

internal val LightColorPalette = lightColorScheme(
    background = Black,
    onBackground = Green,
    primary = Black,
    onPrimary = White,
    primaryContainer = BlueCornflower,
    onPrimaryContainer = White,
    inversePrimary = BlueCornflower,
    secondary = PurpleWildBlueYonder,
    onSecondary = White,
    secondaryContainer = WhiteHeather,
    onSecondaryContainer = White,
    tertiary = WhiteSnuff,
    onTertiary = White,
    tertiaryContainer = WhiteSnuff,
    onTertiaryContainer = White,
    surface = Black,
    onSurface = White,
    surfaceVariant = BlackSilverChalice,
    onSurfaceVariant = GrayMid,
    inverseSurface = BlackSilverChalice,
    inverseOnSurface = BlackDove,
    outline = Green,
    error = RedApricot,
    errorContainer = RedApricot,
    onErrorContainer = White
)
