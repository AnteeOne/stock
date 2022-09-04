package tech.antee.stock.stock_robot.impl

import android.content.Context
import tech.antee.stock.di.qualifiers.ApplicationContext
import tech.antee.stock.stock_robot.api.StockRobot
import javax.inject.Inject

class StockRobotImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : StockRobot {

    override fun startRobot() {
        StockRobotService.startService(context)
    }

    override fun stopRobot() {
        StockRobotService.stopService(context)
    }
}
