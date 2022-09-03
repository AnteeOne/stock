package tech.antee.stock.stock_list.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tech.antee.stock.common_ui.ktx.formatAsPercents
import tech.antee.stock.common_ui.ktx.formatUntilThird
import tech.antee.stock.stock_list.ui.models.StockInListItem
import tech.antee.stock.ui.theme.Dimensions
import tech.antee.stock.ui.theme.StockTheme
import tech.antee.stock.ui_components.extensions.clickableRipple
import kotlin.random.Random

@Composable
fun StockListItemComponent(
    stockModel: StockInListItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(StockListItemDefaults.height),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            TickerImageCard(stockName = stockModel.name, imageUrl = stockModel.imageUrl)
            Column(modifier = Modifier.padding(start = Dimensions.paddingHorizontalS)) {
                Text(
                    text = stockModel.name,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stockModel.ticker.uppercase(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Column(horizontalAlignment = Alignment.End) {
            val percentChangesColor = if (stockModel.percentChange >= 0) Green else Red
            Text(
                text = "\$${stockModel.price.formatUntilThird()}",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.End
            )
            Text(
                text = "${stockModel.priceChange.formatAsPercents()}%",
                style = MaterialTheme.typography.bodyLarge,
                color = percentChangesColor,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
fun TickerCard(
    stockTicker: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .size(StockListItemDefaults.height),
        shape = RoundedCornerShape(Dimensions.cornersM),
        backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stockTicker.substring(0, 1),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.background
            )
        }
    }
}

@Composable
fun TickerImageCard(
    stockName: String,
    imageUrl: String,
    modifier: Modifier = Modifier
) = AsyncImage(
    modifier = modifier.size(StockListItemDefaults.height),
    model = imageUrl,
    contentDescription = stockName
)

@Preview
@Composable
private fun StockListItemPreview() {
    StockTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            StockListItemComponent(
                modifier = Modifier
                    .clickableRipple { }
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                stockModel = StockInListItem(
                    "21",
                    "Alibaba holdings",
                    "BABA",
                    String.format("%.2f", Random.nextDouble(from = 4.0, until = 140.5)).toDouble(),
                    String.format("%.2f", Random.nextDouble(from = -14.0, until = 14.5)).toDouble(),
                    String.format("%.2f", Random.nextDouble(from = -8.0, until = 8.0)).toDouble(),
                    ""
                ),
            )
        }
    }
}

object StockListItemDefaults {
    val height = 52.dp
    val tickerBorder = 1.dp
}
