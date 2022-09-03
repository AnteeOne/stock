package tech.antee.stock.ui_components.loader

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import tech.antee.stock.ui.theme.Dimensions
import tech.antee.stock.ui.theme.StockTheme
import tech.antee.stock.ui_components.R

@Composable
fun Loader(
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onBackground,
    rotationPeriod: Int = LoaderDefaults.rotationPeriod,
) {
    val transition = rememberInfiniteTransition()
    val currentRotation by transition.animateFloat(
        initialValue = LoaderDefaults.initialRotationDegree,
        targetValue = LoaderDefaults.finishRotationDegree,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = rotationPeriod,
                easing = LinearEasing
            )
        )
    )

    Icon(
        modifier = modifier
            .size(LoaderDefaults.size)
            .rotate(currentRotation),
        painter = painterResource(R.drawable.ic_loader),
        tint = tint,
        contentDescription = null,
    )
}

object LoaderDefaults {
    val size: Dp = Dimensions.sizeProgressBarS
    const val rotationPeriod = 1_000
    const val initialRotationDegree = 0f
    const val finishRotationDegree = 360f
}

@Preview
@Composable
private fun LoaderPreview() = StockTheme {
    Loader()
}
