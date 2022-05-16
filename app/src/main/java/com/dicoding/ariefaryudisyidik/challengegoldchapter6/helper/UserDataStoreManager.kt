package com.dicoding.ariefaryudisyidik.challengegoldchapter6.helper

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStoreManager(private val context: Context) {
    companion object {
        private const val DATASTORE_NAME = "user_preferences"
        private val USERNAME_KEY = stringPreferencesKey("username_key")
        private val LOGIN_STATUS_KEY = booleanPreferencesKey("login_status_key")
        private val Context.userDataStore by preferencesDataStore(DATASTORE_NAME)
    }

    suspend fun saveUsername(username: String) {
        context.userDataStore.edit { preferences ->
            preferences[USERNAME_KEY] = username
        }
    }

    fun getUsername(): Flow<String> {
        return context.userDataStore.data.map { preferences ->
            preferences[USERNAME_KEY] ?: "null"
        }
    }

    suspend fun setLoggedInStatus(status: Boolean) {
        context.userDataStore.edit { preferences ->
            preferences[LOGIN_STATUS_KEY] = status
        }
    }

    fun getLoggedInStatus(): Flow<Boolean> {
        return context.userDataStore.data.map { preferences ->
            preferences[LOGIN_STATUS_KEY] ?: false
        }
    }
}