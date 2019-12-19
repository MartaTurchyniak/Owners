package com.magic.Owners.domain.use_cases

import com.magic.Owners.domain.api.UserCall
import com.magic.Owners.domain.models.User
import com.magic.Owners.domain.repository.token.TokenRepository
import com.magic.Owners.domain.repository.user.UserRepository
import io.reactivex.Single
import java.io.File

/**
 * Created by Marta Turchyniak on 2019-12-19.
 */
class UserUseCase(private val userCall: UserCall,
                  private val token: TokenRepository,
                  private val user: UserRepository) {

    fun updateUserData(
        name: String,
        phone: String,
        image: File
    ): Single<User> {
        return userCall.updateUserData(token.getToken(), name, phone, image).flatMap {
            saveData(it)
        }
    }

    fun getUserData(): Single<User> {
        return userCall.getUserData(token.getToken())
//            .flatMap {
//            saveData(it)
//        }
    }

    private fun saveData(it: User): Single<User>? {
        it.name?.let { it1 -> user.saveName(it1) }
        it.phone?.let { it1 -> user.savePhone(it1) }
        it.email?.let { it1 -> user.saveEmail(it1) }
        it.photoUrl?.let { it1 -> user.savePhotoUrl(it1) }
        return Single.just(it)
    }

}