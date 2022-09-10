package tech.antee.stock.stock_robot.impl

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import tech.antee.stock.di.findDependencies
import tech.antee.stock.domain.repositories.RobotRepository
import tech.antee.stock.domain.usecases.CheckStockSubUsecase
import tech.antee.stock.domain.usecases.GetSubStocksUsecase
import tech.antee.stock.stock_robot.impl.di.DaggerStockRobotComponent
import tech.antee.stock.stock_robot.impl.di.StockRobotDependencies
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

// TODO: clean-up service
class StockRobotService : LifecycleService() {

    @Inject
    lateinit var getSubStocksUsecase: GetSubStocksUsecase

    @Inject
    lateinit var checkStockSubUsecase: CheckStockSubUsecase

    @Inject
    lateinit var robotRepository: RobotRepository

    private var robotJob: Job? = null

    override fun onCreate() {
        super.onCreate()

        val deps = application.findDependencies<StockRobotDependencies>()
        DaggerStockRobotComponent.factory().create(deps).inject(this)
        Log.d(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        Log.d(TAG, "onStartCommand: id = $startId, action = ${intent?.action}")
        intent?.action?.let { action ->
            when (action) {
                ACTION_START_ROBOT -> {
                    createNotificationChannel()
                    startServiceInForeground()
                    startRobot()
                }
                ACTION_STOP_ROBOT -> stopService(intent)
                else -> Log.w(TAG, "onStartCommand: id = $startId, unknown action '$action'")
            }
        }
        return START_NOT_STICKY
    }

    private fun startRobot(
        delay: Duration = 2.minutes
    ) {
        if (robotJob != null) {
            Log.d(TAG, "Stock robot is already working")
            return
        }
        robotJob = launchSafely(Dispatchers.IO) {
            while (true) {
                robotRepository.updateState(true)
                getSubStocksUsecase().forEach { subStock ->
                    with(subStock) {
                        checkStockSubUsecase(stockId, price)?.let { subResult ->
                            val notification = NotificationCompat.Builder(this@StockRobotService, CHANNEL_ID)
                                .setSmallIcon(android.R.drawable.btn_star)
                                .setContentTitle(subResult.stockId)
                                .setContentText("Price reached - ${subResult.finalPrice}")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .build()
                            with(NotificationManagerCompat.from(this@StockRobotService)) {
                                val notificationId = 228
                                notify(notificationId, notification)
                            }
                        }
                    }
                }
                delay(delay)
            }
        }
    }

    private suspend fun pauseRobot() {
        // TODO: impl this shit
    }

    override fun onDestroy() {
        super.onDestroy()
        robotJob?.cancel()
        launchSafely { robotRepository.updateState(false) }
        Log.d(TAG, "onDestroy")
    }

    private fun startServiceInForeground(
        serviceId: Int = 0x0001
    ) {
        startForeground(serviceId, buildNotification())
    }

    private fun buildNotification(
        channelId: String = CHANNEL_ID,
        title: String = "Stock Robot",
        text: String = "Stock Robot is Working"
    ): Notification {
        val stopIntent = Intent(this, StockRobotService::class.java).setAction(ACTION_STOP_ROBOT)
        val stopAction = Notification.Action.Builder(
            Icon.createWithResource(this, android.R.drawable.ic_media_pause),
            "Stop",
            PendingIntent.getService(this, 0, stopIntent, PendingIntent.FLAG_IMMUTABLE)
        ).build()
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setSmallIcon(android.R.drawable.ic_menu_mylocation)
                    .setAutoCancel(true)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .addAction(stopAction)
                    .build()
            }
            else -> NotificationCompat.Builder(this, channelId)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(android.R.drawable.ic_menu_mylocation)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(Notification.CATEGORY_SERVICE)
                .addAction(
                    android.R.drawable.ic_media_pause,
                    "Stop",
                    PendingIntent.getService(this, 0, stopIntent, 0)
                )
                .setAutoCancel(false)
                .build()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_NONE
            ).apply {
                lightColor = Color.BLUE
                lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            }
            val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            service.createNotificationChannel(channel)
        }
    }

    private fun handleError(e: Throwable) {
        Log.e(TAG, "handleError: ", e)
    }

    private fun launchSafely(
        context: CoroutineContext = EmptyCoroutineContext,
        scope: CoroutineScope = lifecycleScope,
        onError: ((e: Throwable) -> Boolean)? = null,
        action: suspend () -> Unit
    ): Job = scope.launch(context) {
        try {
            action()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            onError?.invoke(e)?.takeIf { it } ?: handleError(e)
        }
    }

    companion object {

        private const val TAG = "StockRobotService"

        private const val CHANNEL_ID = "Stock"
        private const val CHANNEL_NAME = "Stock"

        private const val ACTION_START_ROBOT = "tech.antee.stock.start_robot"
        private const val ACTION_STOP_ROBOT = "tech.antee.stock.stop_robot"

        fun startService(context: Context) = startService(
            context = context,
            action = ACTION_START_ROBOT,
        )

        fun stopService(context: Context) {
            val intent = Intent(context, StockRobotService::class.java)
            context.stopService(intent)
        }

        private fun startService(context: Context, action: String? = null, extras: Bundle = bundleOf()) {
            Log.d(TAG, "Trying to start service, , action = $action")
            val intent = Intent(context, StockRobotService::class.java)
                .setAction(action)
                .putExtras(extras)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }
    }
}
