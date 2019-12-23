package com.magic.Owners.domain.repository.user

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.magic.Owners.domain.models.User

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
const val USER = "user"
const val USER_REPOSITORY = "user_repository"
class OwnersUserRepository(val context: Context): UserRepository {

    private var preferences: SharedPreferences =
        context.getSharedPreferences(USER_REPOSITORY, Context.MODE_PRIVATE)

    override fun getUser(): User {
        val gson = Gson()
        val json = preferences.getString(USER, "")
        return gson.fromJson<User>(json, User::class.java)
    }

    override fun deleteUser() {
        val prefsEditor = preferences.edit()
        prefsEditor.clear().apply()
    }

    override fun saveUser(user: User) {
        val prefsEditor = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        prefsEditor.putString(USER, json).apply()
    }

    override fun saveName(name: String) {
        val user: User = getUser()
        user.name = name
        saveUser(user)
    }

    override fun savePhotoUrl(url: String) {
        val user: User = getUser()
        user.photoUrl = url
        saveUser(user)
    }

    override fun savePhone(phone: String) {
       val user: User = getUser()
        user.phone = phone
        saveUser(user)
    }

    override fun saveEmail(email: String) {
        val user: User = getUser()
        user.email = email
        saveUser(user)
    }
}