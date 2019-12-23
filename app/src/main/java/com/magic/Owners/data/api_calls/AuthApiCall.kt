package com.magic.Owners.data.api_calls

import com.magic.Owners.data.api_clients.AuthClient
import com.magic.Owners.domain.api.AuthCall
import com.magic.Owners.domain.models.auth.SignInResponse
import com.magic.Owners.domain.models.auth.SignUpResponse
import com.magic.Owners.domain.models.auth.body.AuthSignIn
import com.magic.Owners.domain.models.auth.body.AuthSignUp
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
class AuthApiCall(private val authClient: AuthClient): AuthCall {

    override fun signIn(signIn: AuthSignIn): Single<SignInResponse> {
        return authClient.signIn(signIn)
    }

    override fun signUp(signUp: AuthSignUp): Single<SignUpResponse> {
        return authClient.signUp(signUp)
    }


}