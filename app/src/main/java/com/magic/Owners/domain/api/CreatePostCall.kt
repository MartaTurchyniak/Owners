package com.magic.Owners.domain.api

import com.magic.Owners.domain.models.CreatePost
import io.reactivex.Single
import java.io.File

/**
 * Created by Marta Turchyniak on 10/5/19.
 */
interface CreatePostCall {

    fun createPost(token: String,image: File, title: String, description: String): Single<CreatePost>
}