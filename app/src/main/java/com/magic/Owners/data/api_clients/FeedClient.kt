package com.magic.Owners.data.api_clients

import com.magic.Owners.domain.models.FeedResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Created by Marta Turchyniak on 2019-12-19.
 */
interface FeedClient {

    @GET("/feed")
    fun getFeed(@Header("Authorization") token : String): Single<ArrayList<FeedResponse>>
}