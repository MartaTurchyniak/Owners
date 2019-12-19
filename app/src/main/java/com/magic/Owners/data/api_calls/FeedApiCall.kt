package com.magic.Owners.data.api_calls

import com.magic.Owners.data.api_clients.FeedClient
import com.magic.Owners.domain.api.FeedCall
import com.magic.Owners.domain.models.FeedResponse
import io.reactivex.Single

/**
 * Created by Marta Turchyniak on 2019-12-19.
 */
class FeedApiCall(private val client: FeedClient): FeedCall {

    override fun getFeed(token: String): Single<ArrayList<FeedResponse>> {
        return client.getFeed(token)
    }
}