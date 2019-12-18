package com.magic.Owners.domain.repository.token

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
const val TOKEN = "token"
const val TOKEN_REPOSITORY = "token_repository"
class SimpleTokenRepository(val context: Context): TokenRepository {

    private var preferences: SharedPreferences =
        context.getSharedPreferences(TOKEN_REPOSITORY, Context.MODE_PRIVATE)

    override fun getToken(): String {
        return preferences.getString(TOKEN, "") ?: ""
    }

    override fun deleteToken() {
        preferences.edit().clear().apply()
    }

    override fun saveToken(token: String) {
        preferences.edit()
            .putString(
                TOKEN, token
            ).apply()
    }
}