package tech.antee.stock.ui_components.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role

fun Modifier.clickableRipple(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    rippleIsBounded: Boolean = false,
    onClick: () -> Unit
) = composed {
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = onClick,
        role = role,
        indication = rememberRipple(rippleIsBounded),
        interactionSource = remember { MutableInteractionSource() }
    )
}