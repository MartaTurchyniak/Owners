package com.magic.Owners.presentation.di

import com.magic.Owners.domain.use_cases.AuthUseCase
import com.magic.Owners.domain.use_cases.SimpleGetAllServicesUseCase
import com.magic.Owners.presentation.ui.auth.signin.SignInViewModel
import com.magic.Owners.presentation.ui.auth.signup.SignUpViewModel
import com.magic.Owners.presentation.ui.create_post.CreatePostViewModel
import com.magic.Owners.presentation.ui.feed.FeedViewModel
import com.magic.Owners.presentation.ui.services.ServicesViewModel
import com.magic.Owners.presentation.util.CacheStorageImpl
import com.magic.Owners.presentation.util.CasheStorage
import com.magic.Owners.presentation.util.GalleryPictureResolver
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Marta Turchyniak on 10/5/19.
 */

val presentationModule = module{
   viewModel { ServicesViewModel(SimpleGetAllServicesUseCase(get())) }
   viewModel { SignInViewModel(AuthUseCase(get(), get(), get())) }
   viewModel { SignUpViewModel(AuthUseCase(get(), get(), get())) }
   viewModel { CreatePostViewModel(get()) }
   viewModel { FeedViewModel(get()) }
   single { GalleryPictureResolver(get()) }
   single { CacheStorageImpl(get()) as CasheStorage }
}