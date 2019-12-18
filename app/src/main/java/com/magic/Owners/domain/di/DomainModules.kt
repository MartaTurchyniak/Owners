package com.magic.Owners.domain.di

import com.magic.Owners.domain.repository.token.SimpleTokenRepository
import com.magic.Owners.domain.repository.token.TokenRepository
import com.magic.Owners.domain.repository.user.OwnersUserRepository
import com.magic.Owners.domain.repository.user.UserRepository
import com.magic.Owners.domain.use_cases.impl.AuthUseCase
import com.magic.Owners.domain.use_cases.impl.SimpleCreatePostUseCase
import org.koin.dsl.module

/**
 * Created by Marta Turchyniak on 10/5/19.
 */

val useCasesModule = module {
    single { SimpleCreatePostUseCase(get()) }
    single { AuthUseCase(get(), get(), get()) }
}

val repositoryModule = module {
    single { SimpleTokenRepository(get()) as TokenRepository }
    single { OwnersUserRepository(get()) as UserRepository }
}