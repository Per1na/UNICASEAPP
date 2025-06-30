package com.example.unicase.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

private val TOKEN_KEY = stringPreferencesKey("user_token")
private val NAME_KEY = stringPreferencesKey("user_name")

class UserPreferences(private val context: Context) {

    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
        }
    }

    suspend fun getToken(): String? {
        return context.dataStore.data.map { prefs ->
            prefs[TOKEN_KEY] }.first()
    }

    suspend fun saveName(name: String) {
        context.dataStore.edit { prefs ->
            prefs[NAME_KEY] = name
        }
    }

    fun getName(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[NAME_KEY]
        }
    }

    suspend fun clearToken() {
        context.dataStore.edit { prefs ->
            prefs.remove(TOKEN_KEY)
        }
    }

    suspend fun clearName() {
        context.dataStore.edit { prefs ->
            prefs.remove(NAME_KEY)
        }
    }
}
