package com.magic.Owners.data.api_clients

import com.magic.Owners.domain.models.CreatePost
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * Created by Marta Turchyniak on 10/5/19.
 */
interface CreatePostApiClient {

    @Multipart
    @POST("/post")
    fun upload(
        @Header("Authorization") token : String,
        @Part("tag") tag: String,
        @Part("title") title: String,
        @Part("description") description: String,
        @Part("phone") phone: String,
        @Part("email") email: String,
        @Part body: MultipartBody.Part
    ): Single<CreatePost>
}