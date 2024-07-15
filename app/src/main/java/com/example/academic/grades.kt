package com.example.academic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.academic.databinding.FragmentDashboardBinding
import com.example.academic.databinding.FragmentExamsBinding
import com.example.academic.databinding.FragmentGradesBinding

class grades : Fragment() {
    private lateinit var binding: FragmentGradesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGradesBinding.inflate(inflater,container,false)
        return binding.root
    }


}