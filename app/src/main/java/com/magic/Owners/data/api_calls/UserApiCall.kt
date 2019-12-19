package com.magic.Owners.data.api_calls

import com.magic.Owners.data.api_clients.UserApiClient
import com.magic.Owners.domain.api.UserCall
import com.magic.Owners.domain.models.User
import io.reactivex.Single
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

/**
 * Created by Marta Turchyniak on 2019-12-19.
 */
class UserApiCall(private val client: UserApiClient) : UserCall {

    override fun updateUserData(
        token: String,
        name: String,
        phone: String,
        image: File
    ): Single<User> {
        return client.updateUserInfo(
            token,
            name,
            phone,
            MultipartBody.Part.createFormData(
                "image",
                image.name,
                image.toRequestBody(
                    "image/jpeg".toMediaTypeOrNull()
                )
            )
        )
    }

    override fun getUserData(token: String): Single<User> {
        return client.getUser(token)
    }
}