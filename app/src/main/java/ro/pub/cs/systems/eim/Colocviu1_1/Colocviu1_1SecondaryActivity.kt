package ro.pub.cs.systems.eim.Colocviu1_1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Colocviu1_1SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colocviu1_1_secondary)

        val textView: TextView = findViewById(R.id.textView)
        val coordinates = intent.getStringExtra("COORDINATES")
        textView.setText(coordinates)

        val registerButton: Button = findViewById(R.id.register_button)
        registerButton.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        val cancelButton: Button = findViewById(R.id.cancel_button)
        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}