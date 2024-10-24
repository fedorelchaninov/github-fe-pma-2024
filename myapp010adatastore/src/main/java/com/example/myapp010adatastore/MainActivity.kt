package com.example.myapp010adatastore

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapp010adatastore.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val Context.dataStore by preferencesDataStore(name = "user_prefs")

    val NAME_KEY = stringPreferencesKey("name")
    val AGE_KEY = intPreferencesKey("age")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val age = binding.editTextAge.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    saveData(name, age.toInt())
                }
                Toast.makeText(this, "Data jsou uložené", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Vypnte jméno a věk", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonLoad.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val (name, age) = loadData()
                runOnUiThread {
                    if (!name.isNullOrEmpty() && age != -1) {
                        binding.editTextName.setText(name)
                        binding.editTextAge.setText(age.toString())
                        Toast.makeText(this@MainActivity, "Data jsou načtené", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Data nebyli nalezené", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private suspend fun saveData(name: String, age: Int) {
        applicationContext.dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
            preferences[AGE_KEY] = age
        }
    }

    private suspend fun loadData(): Pair<String?, Int> {
        val preferences = applicationContext.dataStore.data.first()
        val name = preferences[NAME_KEY] ?: ""
        val age = preferences[AGE_KEY] ?: -1
        return Pair(name, age)
    }
}
