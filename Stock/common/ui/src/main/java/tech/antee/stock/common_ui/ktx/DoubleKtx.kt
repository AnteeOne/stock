package tech.antee.stock.common_ui.ktx

import kotlin.math.abs

fun Double.format(digits: Int) = "%.${digits}f".format(this)

fun Double.formatUntilThird() = format(2)

fun Double.formatAsPercents() = abs(this).formatUntilThird() // TODO: move to utils
