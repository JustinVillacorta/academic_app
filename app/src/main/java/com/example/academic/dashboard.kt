package com.example.academic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.academic.databinding.FragmentDashboardBinding
import com.example.academic.databinding.FragmentExamsBinding

class dashboard : Fragment() {
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater,container,false)
        return binding.root
    }


}