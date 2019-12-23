package com.magic.Owners.data.di

import com.magic.Owners.data.api_calls.AuthApiCall
import com.magic.Owners.data.api_calls.CreatePostApiCall
import com.magic.Owners.data.api_calls.FeedApiCall
import com.magic.Owners.data.api_calls.UserApiCall
import com.magic.Owners.data.api_calls.mock.GetServicesMockCall
import com.magic.Owners.domain.api.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Marta Turchyniak on 10/5/19.
 */

val apiCallsModule = module {
    single { CreatePostApiCall(get()) as CreatePostCall }
    single { GetServicesMockCall(androidContext(), get()) as GetAllServicesCall}
    single { AuthApiCall(get()) as AuthCall }
    single { FeedApiCall(get()) as FeedCall }
    single { UserApiCall(get()) as UserCall }
}
