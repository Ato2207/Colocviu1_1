package ro.pub.cs.systems.eim.Colocviu1_1

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class Colocviu1_1Service : Service() {

    private lateinit var processingThread: ProcessingThread

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        Log.d("[Service]", "onCreate() method was invoked")

        val channelId = "my_channel_01"
        val channel = NotificationChannel(
            channelId,
            "Channel human readable title",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
            .createNotificationChannel(channel)

        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("")
            .setContentText("")
            .build()

        startForeground(1, notification)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("[Service]", "onStartCommand() method was invoked")

        val coordinates = intent?.getStringExtra("COORDINATES")
        val processingThread = ProcessingThread(this, coordinates!!)
        processingThread.start()
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        processingThread.stopThread()
    }
}