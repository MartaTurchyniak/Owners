package com.magic.Owners.domain.api

import com.magic.Owners.domain.models.FeedResponse
import io.reactivex.Single

/**
 * Created by Marta Turchyniak on 2019-12-19.
 */
interface FeedCall {

    fun getFeed(token: String): Single<ArrayList<FeedResponse>>
}