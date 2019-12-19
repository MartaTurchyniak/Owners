package com.magic.Owners.data.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.magic.Owners.data.api_clients.AuthClient
import com.magic.Owners.data.api_clients.CreatePostApiClient
import com.magic.Owners.data.api_clients.FeedClient
import com.magic.Owners.data.api_clients.UserApiClient
import com.magic.Owners.data.interceptors.HeadersInterceptor
import com.magic.Owners.data.interceptors.headers.HeaderStorage
import com.magic.Owners.data.interceptors.headers.SimpleHeaderStorage
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Marta Turchyniak on 10/5/19.
 */

val networkModule = module {

    single { SimpleHeaderStorage() as HeaderStorage }
    single { provideDefaultOkhttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideCreatePostApiClient(get()) }
    single { provideGson() }
    single { provideAuthClient(get()) }
    single { provideFeedClient(get()) }
    single { provideUserClient(get()) }
}

fun provideDefaultOkhttpClient(headerStorage: HeaderStorage): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(logging)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(HeadersInterceptor(headerStorage))
        .build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://sometext.xyz/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun provideGson(): Gson {
    return GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
}

fun provideCreatePostApiClient(retrofit: Retrofit): CreatePostApiClient =
    retrofit.create(CreatePostApiClient::class.java)

fun provideAuthClient(retrofit: Retrofit): AuthClient =
    retrofit.create(AuthClient::class.java)

fun provideFeedClient(retrofit: Retrofit): FeedClient =
    retrofit.create(FeedClient::class.java)

fun provideUserClient(retrofit: Retrofit): UserApiClient =
    retrofit.create(UserApiClient::class.java)