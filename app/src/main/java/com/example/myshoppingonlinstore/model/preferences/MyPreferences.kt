package com.example.myshoppingonlinstore.model.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class MyPreferences (val dataStore: DataStore<Preferences>) {
    suspend inline fun <reified T> storeValue(key: Preferences.Key<T>, value: Any) {
        dataStore.edit {
            it[key] = value as T
        }
    }

    suspend inline fun <reified T> readValue(key: Preferences.Key<T>): Flow<T?> =
        dataStore.getFromLocalStorage(key)

    suspend inline fun <reified T> deleteValue(key: Preferences.Key<T>) {
        dataStore.edit {
            it.remove(key)
        }
    }

    suspend inline fun <reified T> DataStore<Preferences>.getFromLocalStorage(
        PreferencesKey: Preferences.Key<T>
    ): Flow<T?> =
        data.catch {
            when (it) {
                is IOException -> emit(emptyPreferences())
                else -> throw it
            }
        }.map {
            it[PreferencesKey]
        }
}