package com.magic.Owners.domain.use_cases

import com.magic.Owners.domain.api.CreatePostCall
import com.magic.Owners.domain.models.CreatePost
import com.magic.Owners.domain.models.CreatePostModel
import com.magic.Owners.domain.repository.token.TokenRepository
import io.reactivex.Single
import java.io.File

/**
 * Created by Marta Turchyniak on 10/5/19.
 */
class SimpleCreatePostUseCase(
    private val token: TokenRepository,
    private val createPostCall: CreatePostCall) {

    fun create(image: File, title: String, description: String): Single<CreatePost> {
        return createPostCall.createPost(token.getToken(), image, title, description)
    }
}