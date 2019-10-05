package com.magic.Owners.data.di

import com.magic.Owners.BuildConfig
import com.magic.Owners.data.api_clients.CreatePostApiClient
import com.magic.Owners.data.interceptors.HeadersInterceptor
import com.magic.Owners.data.interceptors.headers.HeaderStorage
import com.magic.Owners.data.interceptors.headers.SimpleHeaderStorage
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Marta Turchyniak on 10/5/19.
 */

val networkModule = module {

    single { SimpleHeaderStorage() }
    single { provideDefaultOkhttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideCreatePostApiClient(get()) }

}

fun provideDefaultOkhttpClient(headerStorage: HeaderStorage): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HeadersInterceptor(headerStorage))
        .build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun provideCreatePostApiClient(retrofit: Retrofit): CreatePostApiClient = retrofit.create(CreatePostApiClient::class.java)
