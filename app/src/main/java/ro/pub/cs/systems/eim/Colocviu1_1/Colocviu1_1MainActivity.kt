package ro.pub.cs.systems.eim.Colocviu1_1

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class Colocviu1_1MainActivity : AppCompatActivity() {
    private lateinit var southButton: Button
    private lateinit var northButton: Button
    private lateinit var eastButton: Button
    private lateinit var westButton: Button
    private lateinit var textView: TextView

    private lateinit var navigateToSecondaryActivityButton: Button

    private var nrClicks = 0;

    private val intentFilter = IntentFilter()

    private val messageBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent!!.let {
                Log.d("[BROADCAST]", it.action.toString())
                Log.d("[BROADCAST]", it.getStringExtra("COORDINATES").toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colocviu1_1_main)

        southButton = findViewById(R.id.south_button)
        northButton = findViewById(R.id.north_button)
        eastButton = findViewById(R.id.east_button)
        westButton = findViewById(R.id.west_button)
        textView = findViewById(R.id.text_view)

        val activityResultsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Register button was pressed", Toast.LENGTH_LONG).show()
            }
            else if (result.resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Cancel button was pressed", Toast.LENGTH_LONG).show()
            }
        }

        navigateToSecondaryActivityButton = findViewById(R.id.navigate_to_secondary_activity_button)
        navigateToSecondaryActivityButton.setOnClickListener {
            val intent = Intent(this, Colocviu1_1SecondaryActivity::class.java)
            intent.putExtra("COORDINATES", textView.text.toString())
            activityResultsLauncher.launch(intent)
            textView.setText("")
            nrClicks = 0
        }


        southButton.setOnClickListener {
            nrClicks++;
            if (textView.text == "") {
                textView.setText("SOUTH")
            } else {
                textView.setText(textView.text.toString() + ",SOUTH")
            }
            Log.w("NR_CLICKS", "$nrClicks")
        }

        northButton.setOnClickListener {
            nrClicks++
            if (textView.text == "") {
                textView.setText("NORTH")
            } else {
                textView.setText(textView.text.toString() + ",NORTH")
            }
            Log.w("NR_CLICKS", "$nrClicks")
        }

        eastButton.setOnClickListener {
            nrClicks++
            if (textView.text == "") {
                textView.setText("EAST")
            } else {
                textView.setText(textView.text.toString() + ",EAST")
            }
            Log.w("NR_CLICKS", "$nrClicks")
        }

        westButton.setOnClickListener {
            nrClicks++
            if (textView.text == "") {
                textView.setText("WEST")
            } else {
                textView.setText(textView.text.toString() + ",WEST")
            }
            Log.w("NR_CLICKS", "$nrClicks")
        }

        intentFilter.addAction("COORDINATES")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("NR_CLICKS", nrClicks)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState.containsKey("NR_CLICKS")) {
            nrClicks = savedInstanceState.getInt("NR_CLICKS")
        }
    }

    private fun startServiceIfConditionIsMet(coordinates: String) {
        if (nrClicks >= 4) {
            val intent = Intent(applicationContext, Colocviu1_1Service::class.java).apply {
                putExtra("COORDINATES", coordinates)
            }
            applicationContext.startService(intent)
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onResume() {
        super.onResume()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(messageBroadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED)
        } else {
            registerReceiver(messageBroadcastReceiver, intentFilter)
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(messageBroadcastReceiver)
    }

    override fun onDestroy() {
        val intent = Intent(applicationContext, Colocviu1_1Service::class.java)
        applicationContext.stopService(intent)
        super.onDestroy()
    }
}