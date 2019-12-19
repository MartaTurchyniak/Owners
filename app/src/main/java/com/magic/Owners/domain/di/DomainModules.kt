package com.magic.Owners.domain.di

import com.magic.Owners.domain.repository.token.SimpleTokenRepository
import com.magic.Owners.domain.repository.token.TokenRepository
import com.magic.Owners.domain.repository.user.OwnersUserRepository
import com.magic.Owners.domain.repository.user.UserRepository
import com.magic.Owners.domain.use_cases.AuthUseCase
import com.magic.Owners.domain.use_cases.FeedUseCase
import com.magic.Owners.domain.use_cases.SimpleCreatePostUseCase
import com.magic.Owners.domain.use_cases.UserUseCase
import org.koin.dsl.module

/**
 * Created by Marta Turchyniak on 10/5/19.
 */

val useCasesModule = module {
    single { SimpleCreatePostUseCase(get(), get ()) }
    single { AuthUseCase(get(), get(), get()) }
    single { FeedUseCase(get(), get()) }
    single {UserUseCase(get(), get(), get())}
}

val repositoryModule = module {
    single { SimpleTokenRepository(get()) as TokenRepository }
    single { OwnersUserRepository(get()) as UserRepository }
}