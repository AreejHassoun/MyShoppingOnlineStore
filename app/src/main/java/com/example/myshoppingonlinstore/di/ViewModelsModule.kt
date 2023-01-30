package com.example.myshoppingonlinstore.di

import com.example.myshoppingonlinstore.ui.home.HomeFragment
import com.example.myshoppingonlinstore.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module{
    viewModel {
        HomeViewModel()
    }
}
