package com.example.myapp012aimagetoapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var tvColorCounts: TextView
    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        tvColorCounts = findViewById(R.id.tvColorCounts)

        val btnLoadImage: Button = findViewById(R.id.btnLoadImage)
        val btnCountColors: Button = findViewById(R.id.btnCountColors)

        btnLoadImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                type = "image/*"
                addCategory(Intent.CATEGORY_OPENABLE)
            }
            startActivityForResult(intent, 100)
        }

        btnCountColors.setOnClickListener {
            bitmap?.let {
                val colorCounts = countColors(it)
                tvColorCounts.text = colorCounts.entries.joinToString("\n") { entry ->
                    "Barva: #${Integer.toHexString(entry.key)} - Počet pixelů: ${entry.value}"
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data
            uri?.let {
                bitmap = getBitmapFromUri(it)
                imageView.setImageBitmap(bitmap)
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun countColors(bitmap: Bitmap): Map<Int, Int> {
        val colorMap = mutableMapOf<Int, Int>()
        for (x in 0 until bitmap.width) {
            for (y in 0 until bitmap.height) {
                val pixelColor = bitmap.getPixel(x, y)
                colorMap[pixelColor] = colorMap.getOrDefault(pixelColor, 0) + 1
            }
        }
        return colorMap
    }
}