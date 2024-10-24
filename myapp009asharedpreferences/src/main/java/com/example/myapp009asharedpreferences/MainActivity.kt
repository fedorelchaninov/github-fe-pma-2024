package com.example.myapp009asharedpreferences

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp009asharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        binding.buttonSave.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val age = binding.editTextAge.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty()) {
                val editor = sharedPreferences.edit()
                editor.putString("name", name)
                editor.putInt("age", age.toInt())
                editor.apply()
                Toast.makeText(this, "Data jsou uložené", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Vyplnte jméno a věk", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonLoad.setOnClickListener {
            val name = sharedPreferences.getString("name", "")
            val age = sharedPreferences.getInt("age", -1)

            if (!name.isNullOrEmpty() && age != -1) {
                binding.editTextName.setText(name)
                binding.editTextAge.setText(age.toString())
                Toast.makeText(this, "Data jsou načtené", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Data nebyli nalezené", Toast.LENGTH_SHORT).show()
            }
        }
    }
}