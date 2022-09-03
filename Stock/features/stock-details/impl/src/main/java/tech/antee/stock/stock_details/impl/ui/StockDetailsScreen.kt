package tech.antee.stock.stock_details.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tech.antee.stock.common_ui.ktx.formatAsPercents
import tech.antee.stock.stock_details.impl.ui.models.Action
import tech.antee.stock.stock_details.impl.ui.models.StockDetailsItem
import tech.antee.stock.stock_details.impl.ui.models.SubButtonState
import tech.antee.stock.ui.theme.Black
import tech.antee.stock.ui.theme.Dimensions
import tech.antee.stock.ui.theme.Green
import tech.antee.stock.ui.theme.White
import tech.antee.stock.ui_components.button.ButtonState
import tech.antee.stock.ui_components.button.StockButton
import tech.antee.stock.ui_components.charts.LineChart
import tech.antee.stock.ui_components.common.VerticalSpacer
import tech.antee.stock.ui_components.loader.Loader
import tech.antee.stock.ui_components.top_bar.BackButton
import tech.antee.stock.ui_components.top_bar.TopAppBar

@Composable
fun StockDetailsScreen(
    viewModel: StockDetailsViewModel,
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        uiState.stockDetailsItem?.apply {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppBar(
                        title = name,
                        onBackButtonClick = onBackButtonClick
                    )
                    VerticalSpacer(height = 40.dp)
                    StockInfo()
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimensions.paddingHorizontalM)
                ) {
                    StockChartCard()
                    VerticalSpacer(height = 16.dp)
                    SubscribeButton(
                        onClick = { viewModel.onAction(Action.OnSubscribeButtonClick) },
                        subButtonState = uiState.subButtonState
                    ) // TODO:::
                }
            }
        }
        if (uiState.isLoading) Loader(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun AppBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = White,
                textAlign = TextAlign.Center
            )
        },
        leftAction = { BackButton(onClick = onBackButtonClick) }
    )
}

@Composable
fun StockDetailsItem.StockInfo() {
    AsyncImage(
        modifier = Modifier.size(80.dp),
        model = imageUrl,
        contentDescription = name
    )
    VerticalSpacer(height = 16.dp)
    Text(
        text = "$$price",
        style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
        color = Color.White,
        textAlign = TextAlign.Center
    )
    val percentChangesColor = if (percentChange >= 0) Color.Green else Color.Red
    Text(
        text = "${priceChange.formatAsPercents()}%",
        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        color = percentChangesColor,
        textAlign = TextAlign.End
    )
}

@Composable
fun StockDetailsItem.StockChartCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(White.copy(alpha = 0.4f), White.copy(alpha = 0.1f))
                ),
                shape = RoundedCornerShape(Dimensions.cornersM)
            )
            .clip(RoundedCornerShape(Dimensions.cornersM))
            .blur(40.dp),
    ) {
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(top = Dimensions.cornersS),
            data = chartData.map { it.timestamp to it.price },
            graphColor = Green
        )
    }
}

@Composable
fun SubscribeButton(
    modifier: Modifier = Modifier,
    subButtonState: SubButtonState,
    onClick: () -> Unit
) {
    StockButton(
        modifier = modifier,
        buttonState = when (subButtonState) {
            SubButtonState.Loading -> ButtonState.Loading
            SubButtonState.Subbed -> ButtonState.Outlined
            SubButtonState.Unsubbed -> ButtonState.Enabled
        },
        onClick = onClick
    ) {
        val text = when (subButtonState) {
            SubButtonState.Loading -> ""
            SubButtonState.Subbed -> "Unsubscribe"
            SubButtonState.Unsubbed -> "Subscribe"
        }
        Text(
            text = text,
            color = if (subButtonState is SubButtonState.Subbed) White else Black
        )
    }
}
