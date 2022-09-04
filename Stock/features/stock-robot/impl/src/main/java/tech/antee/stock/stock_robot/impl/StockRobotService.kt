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
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class StockRobotService : LifecycleService() {

    private var robotJob: Job? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        Log.d(TAG, "onStartCommand: id = $startId, action = ${intent?.action}")
        intent?.action?.let { action ->
            when (action) {
                ACTION_START_ROBOT -> {
                    startServiceInForeground()
                    startRobot()
                }
                ACTION_STOP_ROBOT -> stopService(intent)
                else -> Log.w(TAG, "onStartCommand: id = $startId, unknown action '$action'")
            }
        }
        return START_NOT_STICKY
    }

    private fun startRobot() {
        if (robotJob != null) {
            Log.d(TAG, "Stock robot is already working")
            return
        }
        robotJob = launchSafely(Dispatchers.IO) {
            while (true) {
                delay(5_000L)
                Log.d(TAG, "Robot is working")
            }
        }
    }

    private suspend fun pauseRobot() {
    }

    override fun onDestroy() {
        super.onDestroy()
        robotJob?.cancel()
        Log.d(TAG, "onDestroy")
    }

    private fun startServiceInForeground(
        serviceId: Int = 0x0001
    ) {
        startForeground(serviceId, buildNotification())
    }

    private fun buildNotification(
        channelId: String = "Stock",
        channelName: String = "Stock",
        title: String = "Stock Robot",
        text: String = "Stock Robot is Working"
    ): Notification {
        val stopIntent = Intent(this, StockRobotService::class.java).setAction(ACTION_STOP_ROBOT)
        val stopAction = Notification.Action.Builder(
            Icon.createWithResource(this, android.R.drawable.ic_media_pause),
            "Stop",
            PendingIntent.getService(this, 0, stopIntent, 0)
        ).build()
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                val notificationChannelId = createNotificationChannel(channelId, channelName)
                Notification.Builder(this, notificationChannelId)
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(
            channelId,
            channelName, NotificationManager.IMPORTANCE_NONE
        )
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
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
