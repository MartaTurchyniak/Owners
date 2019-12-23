package com.magic.Owners.domain.repository.user

import com.magic.Owners.domain.models.User

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
interface UserRepository{
    fun getUser(): User
    fun deleteUser()
    fun saveUser(user: User)
    fun saveName(name:String)
    fun savePhotoUrl(url: String)
    fun savePhone(phone: String)
    fun saveEmail(email: String)
}