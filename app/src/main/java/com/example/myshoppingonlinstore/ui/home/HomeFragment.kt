package com.example.myshoppingonlinstore.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myshoppingonlinstore.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.myshoppingonlinstore.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    private val _viewModel: HomeViewModel by viewModel()
    private lateinit var _binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment.
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return _binding.root
    }
}