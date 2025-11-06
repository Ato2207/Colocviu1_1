package ro.pub.cs.systems.eim.Colocviu1_1

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Colocviu1_1MainActivity : AppCompatActivity() {
    private lateinit var southButton: Button
    private lateinit var northButton: Button
    private lateinit var eastButton: Button
    private lateinit var westButton: Button
    private lateinit var textView: TextView

    private lateinit var navigateToSecondaryActivityButton: Button

    private var nrClicks = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colocviu1_1_main)

        southButton = findViewById(R.id.south_button)
        northButton = findViewById(R.id.north_button)
        eastButton = findViewById(R.id.east_button)
        westButton = findViewById(R.id.west_button)
        textView = findViewById(R.id.text_view)
        navigateToSecondaryActivityButton = findViewById(R.id.navigate_to_secondary_activity_button)

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
}