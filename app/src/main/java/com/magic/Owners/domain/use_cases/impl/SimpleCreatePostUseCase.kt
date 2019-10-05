package com.magic.Owners.domain.use_cases.impl

import com.magic.Owners.domain.api.CreatePostCall
import com.magic.Owners.domain.models.CreatePostModel
import com.magic.Owners.domain.use_cases.CreatePostUseCase

/**
 * Created by Marta Turchyniak on 10/5/19.
 */
class SimpleCreatePostUseCase(private val createPostCall: CreatePostCall) : CreatePostUseCase {

    override fun create(call: CreatePostCall): CreatePostModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}