package com.magic.Owners.domain.api

import com.magic.Owners.domain.models.User
import io.reactivex.Single
import java.io.File

/**
 * Created by Marta Turchyniak on 2019-12-19.
 */
interface UserCall {

    fun updateUserData(token: String, name: String, phone: String, image: File): Single<User>

    fun getUserData(token: String): Single<User>
}