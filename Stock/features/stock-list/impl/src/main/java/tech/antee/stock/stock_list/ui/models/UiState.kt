package tech.antee.stock.stock_list.ui.models

data class UiState(
    val stocks: List<StockInListItem>,
    val isLoading: Boolean,
    val isError: Boolean
) {

    fun isSuccess() = !isLoading

    companion object {
        fun empty() = UiState(
            stocks = emptyList(),
            isLoading = false,
            isError = false
        )
    }
}
