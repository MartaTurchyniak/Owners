package com.magic.Owners.data.api_clients

import com.magic.Owners.domain.models.User
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

/**
 * Created by Marta Turchyniak on 2019-12-19.
 */
interface UserApiClient {

    @GET("/me")
    fun getUser(@Header("Authorization") token : String): Single<User>

    @Multipart
    @PATCH("/me")
    fun updateUserInfo(@Header("Authorization") token : String,
                       @Part("name") name: String,
                       @Part("phone") phone: String,
                       @Part body: MultipartBody.Part): Single<User>
}