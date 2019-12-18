package com.magic.Owners.domain.repository.token

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
interface TokenRepository {
    fun getToken(): String
    fun deleteToken()
    fun saveToken(token: String)
}