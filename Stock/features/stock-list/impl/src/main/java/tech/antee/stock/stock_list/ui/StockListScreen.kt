package tech.antee.stock.stock_list.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tech.antee.stock.stock_list.ui.components.StockListItemComponent
import tech.antee.stock.stock_list.ui.models.Action
import tech.antee.stock.stock_list.ui.models.UiState
import tech.antee.stock.ui.theme.Dimensions
import tech.antee.stock.ui.theme.White
import tech.antee.stock.ui_components.R
import tech.antee.stock.ui_components.common.VerticalSpacer
import tech.antee.stock.ui_components.extensions.clickableRipple
import tech.antee.stock.ui_components.loader.Loader
import tech.antee.stock.ui_components.top_bar.TopAppBar

@Composable
internal fun StockListScreen(
    viewModel: StockListViewModel,
    onStockClick: (stockId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppBar(
                isRobotWorking = uiState.isRobotWorking,
                onRobotClick = { viewModel.onAction(Action.OnStockRobotClick) }
            )
            VerticalSpacer(height = 54.dp)
            Balance()
            VerticalSpacer(height = 72.dp)
            StocksCard(uiState = uiState, onStockClick = onStockClick)
        }
        if (uiState.isLoading) Loader(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun AppBar(
    imageUrl: String = "https://static.pepper.ru/users/raw/default/442073_1/fi/60x60/qt/45/442073_1.jpg",
    isRobotWorking: Boolean,
    onAvatarClick: () -> Unit = {},
    onRobotClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        leftAction = {
            Surface(
                modifier = modifier.size(36.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                RobotButton(
                    isRobotWorking = isRobotWorking,
                    onClick = onRobotClick
                )
            }
        },
        rightAction = {
            Surface(
                modifier = modifier.size(36.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickableRipple(onClick = onAvatarClick),
                    model = imageUrl,
                    contentDescription = "Avatar" // TODO
                )
            }
        }
    )
}

@Composable
private fun RobotButton(
    isRobotWorking: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) = IconButton(
    modifier = modifier.size(Dimensions.sizeIconXs),
    onClick = onClick
) {
    val iconId = if (isRobotWorking) android.R.drawable.ic_media_pause else android.R.drawable.ic_media_play
    Image(
        modifier = Modifier
            .fillMaxSize(),
        painter = painterResource(iconId),
        contentDescription = stringResource(R.string.descr_back)
    )
}

@Composable
private fun Balance(
    balance: String = "$10.243,57",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = balance,
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White,
            textAlign = TextAlign.Center
        )
        VerticalSpacer(height = 4.dp)
        Text(
            text = "Total balance",
            style = MaterialTheme.typography.titleMedium,
            color = White.copy(alpha = 0.4f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun StocksCard(
    uiState: UiState,
    onStockClick: (stockId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(
        topStart = Dimensions.cornersXl,
        topEnd = Dimensions.cornersXl
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(White.copy(alpha = 0.4f), White.copy(alpha = 0.1f))
                ),
                shape = shape
            )
            .blur(40.dp)
            .clip(shape)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalSpacer(height = 18.dp)
            Box( // Badge
                modifier = Modifier
                    .width(40.dp)
                    .height(4.dp)
                    .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(Dimensions.cornersXl))
            )
            VerticalSpacer(height = 8.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimensions.paddingHorizontalM),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Coins",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = White.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
            }
            VerticalSpacer(height = 8.dp)
            LazyColumn(
                modifier = Modifier
            ) {
                if (uiState.isSuccess()) {
                    items(uiState.stocks) { stockItem ->
                        StockListItemComponent(
                            modifier = Modifier
                                .clickableRipple {
                                    onStockClick(stockItem.id)
                                }
                                .padding(
                                    horizontal = Dimensions.paddingHorizontalM,
                                    vertical = Dimensions.paddingVerticalXxs
                                ),
                            stockModel = stockItem
                        )
                    }
                }
            }
        }
    }
}
