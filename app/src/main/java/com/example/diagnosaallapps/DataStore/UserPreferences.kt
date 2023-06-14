package com.example.diagnosaallapps.DataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences  private constructor(private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>){

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val TOKEN_KEY = stringPreferencesKey("Token")

        fun getInstance(dataStore: DataStore<androidx.datastore.preferences.core.Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
    fun getUser(): Flow<UserViewModel> {
        return dataStore.data.map { preferences ->
            UserViewModel(
                preferences[TOKEN_KEY] ?:"",

                )
        }
    }

    suspend fun saveUser(user: UserViewModel) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = user.token

        }
    }

    suspend fun ClearUser() {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] =""
        }
    }

}