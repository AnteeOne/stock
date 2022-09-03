package tech.antee.stock.stock_details.impl.ui.models

data class UiState(
    val stockDetailsItem: StockDetailsItem? = null,
    val subButtonState: SubButtonState,
    val isLoading: Boolean,
    val isError: Boolean
) {

    fun isSuccess() = !isLoading

    fun copyWithError() = copy(isError = true)

    fun copyWithLoading(isLoading: Boolean) = copy(isLoading = isLoading, isError = false)

    fun copyWithSuccess(stockDetailsItem: StockDetailsItem) = copy(
        stockDetailsItem = stockDetailsItem,
        subButtonState = if (stockDetailsItem.isSubbed) SubButtonState.Subbed else SubButtonState.Unsubbed
    )

    companion object {
        fun empty() = UiState(
            stockDetailsItem = null,
            subButtonState = SubButtonState.Loading,
            isLoading = false,
            isError = false
        )
    }
}

sealed interface SubButtonState {
    object Unsubbed : SubButtonState
    object Loading : SubButtonState
    object Subbed : SubButtonState
}
