package tech.antee.stock.stock_list.ui.models

data class UiState(
    val stocks: List<StockInListItem>,
    val isLoading: Boolean,
    val isError: Boolean
) {

    fun isSuccess() = !isLoading

    fun copyWithError() = copy(isError = true)

    fun copyWithLoading(isLoading: Boolean) = copy(isLoading = isLoading, isError = false)

    fun copyWithSuccess(stocks: List<StockInListItem>) = copy(stocks = stocks, isError = false)

    companion object {
        fun empty() = UiState(
            stocks = emptyList(),
            isLoading = false,
            isError = false
        )
    }
}
