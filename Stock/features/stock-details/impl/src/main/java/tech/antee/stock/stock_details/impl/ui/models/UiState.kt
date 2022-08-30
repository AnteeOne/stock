package tech.antee.stock.stock_details.impl.ui.models

data class UiState(
    val stockDetailsItem: StockDetailsItem? = null,
    val isLoading: Boolean,
    val isError: Boolean
) {

    fun isSuccess() = !isLoading

    fun copyWithError() = copy(isError = true)

    fun copyWithLoading(isLoading: Boolean) = copy(isLoading = isLoading, isError = false)

    fun copyWithSuccess(stockDetailsItem: StockDetailsItem) = copy(stockDetailsItem = stockDetailsItem)

    companion object {
        fun empty() = UiState(
            stockDetailsItem = null,
            isLoading = false,
            isError = false
        )
    }
}
