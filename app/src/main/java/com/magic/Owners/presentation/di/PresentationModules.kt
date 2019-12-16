package com.magic.Owners.presentation.di

import com.magic.Owners.domain.use_cases.impl.SimpleGetAllServicesUseCase
import com.magic.Owners.presentation.ui.services.ServicesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Marta Turchyniak on 10/5/19.
 */

val presentationModule = module{
   viewModel { ServicesViewModel(SimpleGetAllServicesUseCase(get())) }
}