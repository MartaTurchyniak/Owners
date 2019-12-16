package com.magic.Owners.data.di

import com.magic.Owners.data.api_calls.CreatePostApiCall
import com.magic.Owners.data.api_calls.GetServicesMockCall
import com.magic.Owners.domain.api.GetAllServicesCall
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.dsl.module

/**
 * Created by Marta Turchyniak on 10/5/19.
 */

val apiCallsModule = module {
    single { CreatePostApiCall(get()) }
    single { GetServicesMockCall(androidContext(), get()) as GetAllServicesCall}
}
