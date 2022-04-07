package com.example.moneymanager.module.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.moneymanager.module.common.model.UserPreference
import com.example.moneymanager.utilities.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException

class AppDataStore(
    private val context: Context
) {

    private object UserPreferenceKeys {
        val isLoggedIn = booleanPreferencesKey(Constants.IS_LOGGED_IN)
        val uID = stringPreferencesKey(Constants.U_ID)
    }

    private val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_PREFERENCE)

    suspend fun saveLoginPreference(value: Boolean) {
        context.appDataStore.edit { preference ->
            preference[UserPreferenceKeys.isLoggedIn] = value
        }
    }

    suspend fun saveUIDPreference(uID: String) {
        context.appDataStore.edit { preference ->
            preference[UserPreferenceKeys.uID] = uID
        }
    }

    suspend fun readUserPreference() = flow {
        context.appDataStore.data
            .catch { e->
                if (e is IOException)
                    emit(UserPreference())
            }
            .map { preference ->
                val isLoggedIn = preference[UserPreferenceKeys.isLoggedIn] ?: false
                val uID = preference[UserPreferenceKeys.uID].orEmpty()
                UserPreference(
                    isLoggedIn = isLoggedIn,
                    uID = uID
                )
            }
    }
}


