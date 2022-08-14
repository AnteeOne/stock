package tech.antee.stock.stock_list.ui

import kotlinx.coroutines.flow.MutableStateFlow
import tech.antee.stock.common_ui.BaseViewModel
import tech.antee.stock.domain.usecases.GetStockUsecase
import tech.antee.stock.stock_list.ui.models.Action
import tech.antee.stock.stock_list.ui.models.Event
import tech.antee.stock.stock_list.ui.models.StockInListItem
import tech.antee.stock.stock_list.ui.models.UiState
import javax.inject.Inject

class StockListViewModel @Inject constructor(
    private val getStockUsecase: GetStockUsecase
) : BaseViewModel<UiState, Event>() {

    override val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.empty())

    init {
        fetchStockList()
    }

    fun onAction(action: Action) {
        when (action) {
            // TODO: add actions
        }
    }

    private fun fetchStockList() {
        launchSafely(
            onLoading = { isLoading ->
                updateState {
                    it.copy(
                        isLoading = isLoading,
                        isError = false
                    )
                }
            }
        ) {
            updateState {
                it.copy(
                    stocks = getStockUsecase().map {
                        with(it) {
                            StockInListItem(
                                id, name, ticker, price, priceChange, percentChange // TODO: map this OAOAOAOAOAA
                            )
                        }
                    },
                    isError = false
                )
            }
        }
    }
}
