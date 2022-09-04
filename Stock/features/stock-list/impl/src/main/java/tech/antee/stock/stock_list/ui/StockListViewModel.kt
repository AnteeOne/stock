package tech.antee.stock.stock_list.ui

import kotlinx.coroutines.flow.MutableStateFlow
import tech.antee.stock.common_ui.BaseViewModel
import tech.antee.stock.domain.repositories.RobotRepository
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
    private val stockRobotRepository: RobotRepository,
    private val mapper: StockInListUiMapper
) : BaseViewModel<UiState, Event>() {

    override val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.empty())

    init {
        fetchStockList()
        observeRobotState()
    }

    fun onAction(action: Action) {
        when (action) {
            Action.OnStockRobotClick -> if (!uiState.value.isRobotWorking) stockRobot.startRobot() else stockRobot.stopRobot()
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

    private fun observeRobotState() {
        launchSafely {
            stockRobotRepository.workingStateFlow.collect { isWorking ->
                updateState { it.copy(isRobotWorking = isWorking) }
            }
        }
    }

    override fun onError(exception: Throwable) {
        super.onError(exception)
        updateState { it.copyWithError() }
        exception.printStackTrace()
    }
}
