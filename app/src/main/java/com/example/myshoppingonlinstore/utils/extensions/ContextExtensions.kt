package com.example.myshoppingonlinstore.utils.extensions

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.myshoppingonlinstore.utils.DATA_STORE_NAME

/**
 * ContextExtension
 *
 */


// Create data store singleton as context extension to use it anywhere.
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)