package tech.antee.stock.stock_details.impl.ui

import kotlinx.coroutines.flow.MutableStateFlow
import tech.antee.stock.common_ui.BaseViewModel
import tech.antee.stock.di.qualifiers.StockId
import tech.antee.stock.domain.usecases.GetStockDetailsUsecase
import tech.antee.stock.domain.usecases.HandleSubUsecase
import tech.antee.stock.stock_details.impl.ui.mappers.StockDetailsUiMapper
import tech.antee.stock.stock_details.impl.ui.models.Action
import tech.antee.stock.stock_details.impl.ui.models.Event
import tech.antee.stock.stock_details.impl.ui.models.SubButtonState
import tech.antee.stock.stock_details.impl.ui.models.UiState
import javax.inject.Inject

class StockDetailsViewModel @Inject constructor(
    @StockId
    private val stockId: String,
    private val getStockDetailsUsecase: GetStockDetailsUsecase,
    private val handleSubUsecase: HandleSubUsecase,
    private val mapper: StockDetailsUiMapper
) : BaseViewModel<UiState, Event>() {

    override val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.empty())

    init {
        observeStockDetails()
    }

    fun onAction(action: Action) {
        when (action) {
            Action.OnSubscribeButtonClick -> handleSubscribeButton()
        }
    }

    private fun observeStockDetails() {
        launchSafely(
            onLoading = { isLoading -> updateState { it.copyWithLoading(isLoading) } }
        ) {
            getStockDetailsUsecase(stockId).collect { stockDetails ->
                updateState {
                    it.copyWithSuccess(mapper.mapFromDomain(stockDetails))
                }
                updateState { it.copyWithLoading(false) }
            }
        }
    }

    private fun handleSubscribeButton() {
        uiState.value.stockDetailsItem?.let { stockDetails ->
            launchSafely {
                updateState { it.copy(subButtonState = SubButtonState.Loading) }
                handleSubUsecase(stockId, stockDetails.price, stockDetails.isSubbed)
            }
        }
    }

    override fun onError(exception: Throwable) {
        super.onError(exception)
        updateState { it.copyWithError() }
        exception.printStackTrace()
    }
}
