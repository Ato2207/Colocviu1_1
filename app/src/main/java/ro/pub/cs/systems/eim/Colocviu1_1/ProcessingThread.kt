package ro.pub.cs.systems.eim.Colocviu1_1

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Thread.sleep
import java.time.LocalDateTime

class ProcessingThread(private val context: Context, private val coordinates: String): Thread() {

    private var isRunning = true
    @RequiresApi(Build.VERSION_CODES.O)
    override fun run() {
        while (isRunning) {
            sleep()
            sendMessage()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendMessage() {
        val intent = Intent()

        intent.action = "COORDINATES"
        intent.putExtra("data", LocalDateTime.now().toString() + " " + coordinates)

        context.sendBroadcast(intent)
    }

    private fun sleep() {
        try {
            sleep(20000L)
        } catch (interruptedException: InterruptedException) {
            interruptedException.printStackTrace()
        }
    }

    fun stopThread() {
        isRunning = false
    }
}