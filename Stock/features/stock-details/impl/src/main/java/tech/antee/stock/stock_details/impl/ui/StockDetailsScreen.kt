package tech.antee.stock.stock_details.impl.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import tech.antee.stock.ui_components.charts.LineChart

@Composable
fun StockDetailsScreen(
    viewModel: StockDetailsViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        uiState.stockDetailsItem?.apply {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier) {
                }
                LineChart(
                    modifier = Modifier
                        .border(width = 1.dp, color = Color.Black)
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    data = chartData.map { it.timestamp to it.price }
                )
            }
        }
        if (uiState.isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}
