package com.example.myapp008bfragmentsexample1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp008bfragmentsexample1.databinding.ActivityMainBinding
import com.example.myapp008bfragmentsexample1.fragments.FragmentList
import com.example.myapp008bfragmentsexample1.fragments.ImageFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var imageFragment: ImageFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, FragmentList())
            .commit()

        imageFragment = ImageFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.imageFragmentContainer, imageFragment)
            .commit()
    }

    fun showImage(imageResId: Int) {
        imageFragment.updateImage(imageResId)
    }
}