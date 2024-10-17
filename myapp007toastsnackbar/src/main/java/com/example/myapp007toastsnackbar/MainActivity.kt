package com.example.myapp007toastsnackbar

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp007toastsnackbar.databinding.ActivityMainBinding
import com.example.myapp007toastsnackbar.databinding.CustomToastBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inflater: LayoutInflater = layoutInflater
        val customToastBinding = CustomToastBinding.inflate(inflater)

        binding.toastButton.setOnClickListener {
            Toast.makeText(this, "Toto je Toast zpráva", Toast.LENGTH_SHORT).show()
            val toast = Toast(applicationContext)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = customToastBinding.root
            toast.show()
        }

        binding.snackbarButton.setOnClickListener {
            Snackbar.make(it, "Toto je Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Zpět") {
                    Toast.makeText(this, "Akce Snackbaru kliknuta", Toast.LENGTH_SHORT).show()
                }.show()
        }
    }
}