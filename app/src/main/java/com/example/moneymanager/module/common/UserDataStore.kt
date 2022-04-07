package com.example.moneymanager.module.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.moneymanager.module.auth.domain.model.User
import com.example.moneymanager.utilities.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserDataStore(
    private val context: Context
) {

     object UserPreferenceKeys {
        val isLoggedIn = booleanPreferencesKey(Constants.IS_LOGGED_IN)
        val isEmailVerified = booleanPreferencesKey(Constants.IS_EMAIL_VERIFIED)
        val uID = stringPreferencesKey(Constants.U_ID)
        val phone = stringPreferencesKey(Constants.PHONE)
        val email = stringPreferencesKey(Constants.EMAIL)
        val username = stringPreferencesKey(Constants.USERNAME)
        val providerID = stringPreferencesKey(Constants.PROVIDER_ID)
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

    suspend fun saveEmailPreference(email: String) {
        context.appDataStore.edit { preference ->
            preference[UserPreferenceKeys.email] = email
        }
    }

    suspend fun savePhonePreference(phone: String) {
        context.appDataStore.edit { preference ->
            preference[UserPreferenceKeys.phone] = phone
        }
    }

    suspend fun saveUsernamePreference(username: String) {
        context.appDataStore.edit { preference ->
            preference[UserPreferenceKeys.username] = username
        }
    }

    suspend fun saveProviderIdPreference(providerId: String) {
        context.appDataStore.edit { preference ->
            preference[UserPreferenceKeys.providerID] = providerId
        }
    }

    suspend fun saveEmailVerifyPreference(isEmailVerified: Boolean) {
        context.appDataStore.edit { preference ->
            preference[UserPreferenceKeys.isEmailVerified] = isEmailVerified
        }
    }

    suspend fun readUserPreference() = flow {
        context.appDataStore.data
            .catch { e->
                if (e is IOException)
                    emit(User())
            }
            .map { preference ->
                val isLoggedIn = preference[UserPreferenceKeys.isLoggedIn] ?: false
                val uID = preference[UserPreferenceKeys.uID].orEmpty()
                val email = preference[UserPreferenceKeys.email].orEmpty()
                val phone = preference[UserPreferenceKeys.phone].orEmpty()
                val isEmailVerified = preference[UserPreferenceKeys.isEmailVerified] ?: false
                val username = preference[UserPreferenceKeys.username].orEmpty()
                val providerId = preference[UserPreferenceKeys.providerID].orEmpty()

                User(
                    isLoggedIn = isLoggedIn,
                    uId = uID,
                    email = email,
                    phone = phone,
                    username = username,
                    providerId = providerId,
                    isEmailVerified = isEmailVerified
                )
            }
    }
}


