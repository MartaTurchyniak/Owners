package com.magic.Owners.domain.use_cases

import com.magic.Owners.domain.api.FeedCall
import com.magic.Owners.domain.models.FeedResponse
import com.magic.Owners.domain.repository.token.TokenRepository
import io.reactivex.Single

/**
 * Created by Marta Turchyniak on 2019-12-19.
 */
class FeedUseCase(private val feedCall: FeedCall,
                  private val tokenRepository: TokenRepository) {

    fun getFeed(): Single<ArrayList<FeedResponse>> {
        return feedCall.getFeed(tokenRepository.getToken())
    }
}