package tech.antee.stock.stock_list.ui.models

sealed interface Action {
    object OnStockRobotClick : Action
}
