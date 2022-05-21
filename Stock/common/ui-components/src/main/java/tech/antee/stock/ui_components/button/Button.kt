package tech.antee.stock.ui_components.button

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import tech.antee.stock.ui.theme.Dimensions

@Composable
fun StockButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonState: ButtonState = ButtonState.Enabled,
    shape: Shape = RoundedCornerShape(Dimensions.cornersXs),
    content: @Composable (RowScope) -> Unit
) {
    when (buttonState) {
        ButtonState.Disabled,
        ButtonState.Enabled -> Button(
            modifier = modifier,
            onClick = onClick,
            isEnabled = buttonState is ButtonState.Enabled,
            shape = shape,
            content = content
        )
        ButtonState.Outlined -> OutlinedButton(
            modifier = modifier,
            onClick = onClick,
            isEnabled = true,
            shape = shape,
            content = content
        )
        ButtonState.Loading -> LoadingButton(
            modifier = modifier,
            shape = shape
        )
    }
}

@Composable
private fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    shape: Shape,
    content: @Composable (RowScope) -> Unit
) = Button(
    modifier = modifier
        .fillMaxWidth()
        .height(Dimensions.heightButtonM),
    shape = shape,
    colors = with(MaterialTheme.colorScheme) {
        ButtonDefaults.buttonColors(
            disabledContainerColor = primary.copy(alpha = 0.5f),
            disabledContentColor = onPrimaryContainer
        )
    },
    enabled = isEnabled,
    onClick = onClick,
) {
    ProvideButtonTextStyle {
        content(this)
    }
}

@Composable
private fun OutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    shape: Shape,
    content: @Composable (RowScope) -> Unit
) = OutlinedButton(
    modifier = modifier
        .fillMaxWidth()
        .height(Dimensions.heightButtonM),
    enabled = isEnabled,
    shape = shape,
    onClick = onClick,
) {
    ProvideButtonTextStyle {
        content(this)
    }
}

@Composable
private fun LoadingButton(
    shape: Shape,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimensions.heightButtonM),
        shape = shape,
        onClick = onClick
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(Dimensions.sizeProgressBarS)
                .align(Alignment.CenterVertically),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun ProvideButtonTextStyle(content: @Composable () -> Unit) = ProvideTextStyle(
    value = MaterialTheme
        .typography
        .bodyLarge
        .copy(textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold),
    content = content
)

sealed interface ButtonState {
    object Enabled : ButtonState
    object Loading : ButtonState
    object Outlined : ButtonState
    object Disabled : ButtonState
}
