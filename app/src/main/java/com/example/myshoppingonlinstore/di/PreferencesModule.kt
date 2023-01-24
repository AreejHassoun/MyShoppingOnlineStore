package com.example.myshoppingonlinstore.di

import com.example.myshoppingonlinstore.model.preferences.MyPreferences
import com.example.myshoppingonlinstore.model.preferences.PreferencesRepo
import com.example.myshoppingonlinstore.model.preferences.PreferencesRepository
import com.example.myshoppingonlinstore.utils.extensions.dataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferencesModule = module{
    //Provide instance of [DataStore<Preferences>].
    single {
        androidContext().dataStore
    }
    // Provide instance of [MyPreferences].
    single {
        MyPreferences(get())
    }
    // Provide instance of [PreferencesRepo].
    single<PreferencesRepo> {
        PreferencesRepository(get(), get())
    }
}