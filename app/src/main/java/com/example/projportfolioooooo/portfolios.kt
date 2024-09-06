package com.example.projportfolioooooo

import android.annotation.SuppressLint
import android.content.Intent
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class portfolios : AppCompatActivity() {

    fun captureViewAsBitmap(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    fun saveBitmapToFile(context: Context, bitmap: Bitmap, fileName: String): File? {
        val file = File(context.getExternalFilesDir(null), fileName)
        return try {
            FileOutputStream(file).use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.flush()
            }
            file
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageView = findViewById(R.id.imageView)
        saveButton = findViewById(R.id.button3)

        saveButton.setOnClickListener {
            checkStoragePermission()
        }

        setContentView(R.layout.portfolios)
        val button: Button = findViewById(R.id.button5)
        button.setOnClickListener{
            val intent= Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }

        val textViewDisplay: TextView = findViewById(R.id.textViewDisplay)
        val textViewDisplay2: TextView = findViewById(R.id.ViewAGE)
        val textViewDisplay3 :TextView =findViewById(R.id.occupation)
        val textViewDisplay4 :TextView =findViewById(R.id.myinterests)


        // Get the data from the Intent
        val data = intent.getStringExtra("EXTRA_DATA")
        val data2 = intent.getStringExtra("EXTRA_DATA2")
        val data3 = intent.getStringExtra("EXTRA_DATA3")
        val data4 = intent.getStringExtra("EXTRA_DATA4")



        // Display the data
        textViewDisplay.text = data
        textViewDisplay2.text = data2
        textViewDisplay3.text = data3
        textViewDisplay4.text = data4

    }
    private lateinit var imageView: ImageView
    private lateinit var saveButton: Button

    companion object {
        private const val REQUEST_WRITE_STORAGE = 1
    }


    private fun checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_WRITE_STORAGE)
        } else {
            saveScreen()
        }
    }

    private fun saveScreen() {
        val bitmap = captureViewAsBitmap(imageView)
        val file = saveBitmapToFile(this, bitmap, "screenshot.png")
        if (file != null) {
            // Notify user or perform actions with the saved file
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_WRITE_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveScreen()
            } else {
                // Handle permission denial
            }
        }
    }
}
