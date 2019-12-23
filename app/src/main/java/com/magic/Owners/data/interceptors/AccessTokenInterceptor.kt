package com.magic.Owners.data.interceptors

import com.magic.Owners.domain.repository.token.TokenRepository
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
//class AccessTokenInterceptor(private val accessTokenProvider: TokenRepository) : Interceptor {

//    override fun intercept(chain: Interceptor.Chain): Response {
//        val token = accessTokenProvider.getToken()
//
//        return if (token == null) {
//            chain.proceed(chain.request())
//        } else {
//            val authenticatedRequest = chain.request()
//                .newBuilder()
//                .addHeader("Authorization", "Bearer $token")
//                .build()
//            chain.proceed(authenticatedRequest)
//        }
//    }
//}
