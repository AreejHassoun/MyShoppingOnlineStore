package com.example.myshoppingonlinstore.di

import com.example.myshoppingonlinstore.model.ModelRepo
import com.example.myshoppingonlinstore.model.ModelRepository
import org.koin.dsl.module

val modelModule = module{
    // Provide instance of [ModelRepo].
    single<ModelRepo> {
        ModelRepository(get(), get())
    }
}