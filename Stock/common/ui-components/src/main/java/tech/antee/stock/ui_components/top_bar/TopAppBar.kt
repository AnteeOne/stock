package tech.antee.stock.ui_components.top_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tech.antee.stock.ui.theme.Dimensions
import tech.antee.stock.ui_components.R

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit)? = null,
    leftAction: @Composable (() -> Unit)? = null,
    rightAction: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .height(TopAppBarDefaults.height)
            .padding(horizontal = Dimensions.paddingHorizontalM),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row { leftAction?.invoke() }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            title?.invoke()
        }
        Row { rightAction?.invoke() }
    }
}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) = IconButton(
    modifier = modifier.size(Dimensions.sizeIconXs),
    onClick = onClick
) {
    Image(
        modifier = Modifier
            .fillMaxSize(),
        painter = painterResource(R.drawable.ic_back),
        contentDescription = stringResource(R.string.descr_back)
    )
}

@Composable
fun CloseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) = IconButton(
    modifier = modifier.size(Dimensions.sizeIconXs),
    onClick = onClick
) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(R.drawable.ic_close),
        contentDescription = stringResource(R.string.descr_close)
    )
}

private object TopAppBarDefaults {
    val height = 56.dp
}
