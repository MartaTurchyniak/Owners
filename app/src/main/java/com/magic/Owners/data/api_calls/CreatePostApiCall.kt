package com.magic.Owners.data.api_calls

import com.magic.Owners.data.api_clients.CreatePostApiClient
import com.magic.Owners.domain.api.CreatePostCall
import com.magic.Owners.domain.models.CreatePost
import com.magic.Owners.domain.repository.token.TokenRepository
import io.reactivex.Single
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

/**
 * Created by Marta Turchyniak on 10/5/19.
 */
class CreatePostApiCall(private val client: CreatePostApiClient) : CreatePostCall{

    override fun createPost(token: String, image: File, title: String, description: String): Single<CreatePost> {
        return client.upload(
            token,
            "home", title, description, "", "",
            MultipartBody.Part.createFormData(
                "image",
                image.name,
                image.toRequestBody(
                    "image/jpeg".toMediaTypeOrNull()
                )
            )
        )
    }


}