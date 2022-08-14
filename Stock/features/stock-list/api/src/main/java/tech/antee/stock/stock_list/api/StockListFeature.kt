package tech.antee.stock.stock_list.api

import tech.antee.stock.multi_compose.ComposableFeature

abstract class StockListFeature : ComposableFeature {

    override val featureRoute: String = "stock_list"
}
