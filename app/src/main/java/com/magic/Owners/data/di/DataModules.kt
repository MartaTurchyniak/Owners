package com.magic.Owners.data.di

import com.magic.Owners.data.api_calls.CreatePostApiCall
import org.koin.dsl.module

/**
 * Created by Marta Turchyniak on 10/5/19.
 */

val apiCallsModule = module {
    single { CreatePostApiCall(get()) }
}
