package com.magic.Owners.domain.api

import com.magic.Owners.domain.models.auth.SignInResponse
import com.magic.Owners.domain.models.auth.SignUpResponse
import com.magic.Owners.domain.models.auth.body.AuthSignIn
import com.magic.Owners.domain.models.auth.body.AuthSignUp
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
interface AuthCall {

    fun signIn(signIn: AuthSignIn): Single<SignInResponse>

    fun signUp(signUp: AuthSignUp): Single<SignUpResponse>
}