package tech.antee.stock.ui_components.bottom_navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import tech.antee.stock.ui.theme.Dimensions

@Composable
fun RoundedNavigationBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = BottomNavigationDefaults.Elevation,
    content: @Composable RowScope.() -> Unit
) = with(Dimensions) {
    Surface(
        modifier = modifier,
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = RoundedCornerShape(topStart = cornersL, topEnd = cornersL)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(RoundedNavigationBarDefaults.navigationBarHeight)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}

private object RoundedNavigationBarDefaults {
    val navigationBarHeight = 56.dp
}
