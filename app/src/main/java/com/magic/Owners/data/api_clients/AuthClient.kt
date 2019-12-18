package com.magic.Owners.data.api_clients

import com.magic.Owners.domain.models.auth.SignInResponse
import com.magic.Owners.domain.models.auth.SignUpResponse
import com.magic.Owners.domain.models.auth.body.AuthSignIn
import com.magic.Owners.domain.models.auth.body.AuthSignUp
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
interface AuthClient {

    @POST("/auth/signup")
    fun signUp( @Body user: AuthSignUp): Single<SignUpResponse>

    @POST("/auth/login")
    fun signIn(@Body user: AuthSignIn): Single<SignInResponse>
}