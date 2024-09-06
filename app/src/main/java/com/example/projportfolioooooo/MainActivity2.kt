package com.example.projportfolioooooo

import android.content.Intent
import android.icu.text.Edits
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import java.util.jar.Attributes.Name

class MainActivity2 : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main2)


            val Text: EditText = findViewById(R.id.Text)
            val Text2: EditText = findViewById(R.id.Text2)
            val Text3: EditText = findViewById(R.id.Text3)
            val Text4: EditText = findViewById(R.id.Text4)
            val buttonSave: Button = findViewById(R.id.button2)

            buttonSave.setOnClickListener {
                // Get the input data
                val inputData = Text.text.toString()
                val inputData2 = Text2.text.toString()
                val inputData3 = Text3.text.toString()
                val inputData4 = Text4.text.toString()


                // Create an Intent to start the DisplayActivity
                val intent = Intent(this, portfolios::class.java).apply {
                    putExtra("EXTRA_DATA", inputData)
                    putExtra("EXTRA_DATA2", inputData2)
                    putExtra("EXTRA_DATA3", inputData3)
                    putExtra("EXTRA_DATA4", inputData4)


                // Pass the data
                }

                startActivity(intent) // Start the DisplayActivity
            }
        }
}