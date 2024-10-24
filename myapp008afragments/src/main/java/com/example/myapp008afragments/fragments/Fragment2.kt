package com.example.myapp008afragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp008afragments.databinding.Fragment2Binding

class Fragment2 : Fragment() {

    private var _binding: Fragment2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Fragment2Binding.inflate(inflater, container, false)

        binding.textViewFragment2.text = "Toto je druhý fragment a je lepší"

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}