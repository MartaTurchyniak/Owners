package com.magic.Owners.data.api_calls

import com.magic.Owners.data.api_clients.CreatePostApiClient
import com.magic.Owners.domain.api.CreatePostCall

/**
 * Created by Marta Turchyniak on 10/5/19.
 */
class CreatePostApiCall(val client: CreatePostApiClient) : CreatePostCall{
}