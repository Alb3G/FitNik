package com.example.fitnik.core.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

private object PreferencesKeys {
    val USER_ID = stringPreferencesKey("user_id")
}

@Singleton
class UserPreferencesManager @Inject constructor(
    @ApplicationContext context: Context
) {

    private val dataStore = context.dataStore

    suspend fun saveUserId(uid: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_ID] = uid
        }
    }

    fun getUserId(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.USER_ID]
        }
    }

    fun isLoggedIn(): Flow<Boolean> {
        return getUserId().map { userId ->
            userId != null && userId.isNotEmpty()
        }
    }

    suspend fun clearUserId() {
        dataStore.edit { prefs ->
            prefs.remove(PreferencesKeys.USER_ID)
        }
    }

}