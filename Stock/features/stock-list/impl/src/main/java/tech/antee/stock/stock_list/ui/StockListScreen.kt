package tech.antee.stock.stock_list.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import tech.antee.stock.stock_list.ui.components.StockListItemComponent
import tech.antee.stock.ui.theme.Dimensions
import tech.antee.stock.ui_components.extensions.clickableRipple

@Composable
internal fun StockListScreen(
    viewModel: StockListViewModel,
    onStockClick: (stockId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.fillMaxSize()) {
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
            if (uiState.isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
