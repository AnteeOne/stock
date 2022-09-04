package tech.antee.stock.stock_list.ui

import kotlinx.coroutines.flow.MutableStateFlow
import tech.antee.stock.common_ui.BaseViewModel
import tech.antee.stock.domain.usecases.GetStockListUsecase
import tech.antee.stock.stock_list.ui.mappers.StockInListUiMapper
import tech.antee.stock.stock_list.ui.models.Action
import tech.antee.stock.stock_list.ui.models.Event
import tech.antee.stock.stock_list.ui.models.UiState
import tech.antee.stock.stock_robot.api.StockRobot
import javax.inject.Inject

class StockListViewModel @Inject constructor(
    private val getStockListUsecase: GetStockListUsecase,
    private val stockRobot: StockRobot,
    private val mapper: StockInListUiMapper
) : BaseViewModel<UiState, Event>() {

    override val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.empty())

    init {
        fetchStockList()
    }

    fun onAction(action: Action) {
        when (action) {
            // TODO: add actions
            Action.OnStockRobotClick -> {
                stockRobot.startRobot()
            }
        }
    }

    private fun fetchStockList() {
        launchSafely(
            onLoading = { isLoading -> updateState { it.copyWithLoading(isLoading) } }
        ) {
            updateState {
                it.copy(
                    stocks = getStockListUsecase().map(mapper::mapFromDomain),
                    isError = false
                )
            }
        }
    }

    override fun onError(exception: Throwable) {
        super.onError(exception)
        updateState { it.copyWithError() }
        exception.printStackTrace()
    }
}
