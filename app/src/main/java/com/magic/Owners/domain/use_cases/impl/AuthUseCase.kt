package com.magic.Owners.domain.use_cases.impl

import com.magic.Owners.domain.api.AuthCall
import com.magic.Owners.domain.models.auth.SignInResponse
import com.magic.Owners.domain.models.auth.SignUpResponse
import com.magic.Owners.domain.models.auth.body.AuthSignIn
import com.magic.Owners.domain.models.auth.body.AuthSignUp
import com.magic.Owners.domain.repository.token.TokenRepository
import com.magic.Owners.domain.repository.user.UserRepository
import io.reactivex.Single

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
class AuthUseCase(private val call: AuthCall,
                  private val tokenRepository: TokenRepository,
                  private val userRepository: UserRepository
) {

    fun signUp(email: String, password: String, phone: String): Single<SignUpResponse>{
       return call.signUp(AuthSignUp(email, password, phone)).flatMap{
           tokenRepository.saveToken(it.token)
           userRepository.saveUser(it.user)
           Single.just(it)
       }
    }

    fun signIn(email: String, password: String): Single<SignInResponse>{
        return call.signIn(AuthSignIn(email, password)).flatMap{
            tokenRepository.saveToken(it.token)
            Single.just(it)
        }
    }
}