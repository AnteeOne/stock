package tech.antee.stock.stock_details.impl.ui.models

sealed interface Action {
    object OnSubscribeButtonClick : Action
}
