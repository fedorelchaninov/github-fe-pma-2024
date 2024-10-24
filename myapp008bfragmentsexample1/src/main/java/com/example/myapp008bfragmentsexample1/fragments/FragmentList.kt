package com.example.myapp008bfragmentsexample1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.myapp008bfragmentsexample1.MainActivity
import com.example.myapp008bfragmentsexample1.R
import com.example.myapp008bfragmentsexample1.databinding.FragmentListBinding

class FragmentList : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val items = listOf("Sour", "Palace", "Hockey")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val selectedImage = when (position) {
                0 -> R.drawable.sour
                1 -> R.drawable.palace
                else -> R.drawable.hockey
            }
            (activity as MainActivity).showImage(selectedImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
