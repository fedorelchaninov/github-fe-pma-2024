package com.example.myapp008afragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.myapp008afragments.databinding.ActivityMainBinding
import com.example.myapp008afragments.fragments.Fragment1
import com.example.myapp008afragments.fragments.Fragment2

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonFragment1.setOnClickListener {
            replaceFragment(Fragment1())
        }

        binding.buttonFragment2.setOnClickListener {
            replaceFragment(Fragment2())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}