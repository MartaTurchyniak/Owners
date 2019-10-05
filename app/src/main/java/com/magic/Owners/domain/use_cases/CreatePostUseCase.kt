package com.magic.Owners.domain.use_cases

import com.magic.Owners.domain.api.CreatePostCall
import com.magic.Owners.domain.models.CreatePostModel

/**
 * Created by Marta Turchyniak on 10/5/19.
 */
interface CreatePostUseCase{

    fun create(call: CreatePostCall): CreatePostModel
}