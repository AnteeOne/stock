package tech.antee.stock.stock_details.impl.ui

import kotlinx.coroutines.flow.MutableStateFlow
import tech.antee.stock.common_ui.BaseViewModel
import tech.antee.stock.di.qualifiers.StockId
import tech.antee.stock.domain.usecases.GetStockDetailsUsecase
import tech.antee.stock.stock_details.impl.ui.mappers.StockDetailsUiMapper
import tech.antee.stock.stock_details.impl.ui.models.Event
import tech.antee.stock.stock_details.impl.ui.models.UiState
import javax.inject.Inject

class StockDetailsViewModel @Inject constructor(
    @StockId
    private val stockId: String,
    private val getStockDetailsUsecase: GetStockDetailsUsecase,
    private val mapper: StockDetailsUiMapper
) : BaseViewModel<UiState, Event>() {

    override val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.empty())

    init {
        fetchStockDetails()
    }

    fun onAction() {
    }

    private fun fetchStockDetails() {
        launchSafely(
            onLoading = { isLoading -> updateState { it.copyWithLoading(isLoading) } }
        ) {
            updateState {
                it.copyWithSuccess(mapper.mapFromDomain(getStockDetailsUsecase(stockId)))
            }
        }
    }

    override fun onError(exception: Throwable) {
        super.onError(exception)
        updateState { it.copyWithError() }
        exception.printStackTrace()
    }
}
