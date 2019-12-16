package com.magic.Owners.domain.di

import com.magic.Owners.domain.use_cases.impl.SimpleCreatePostUseCase
import org.koin.dsl.module

/**
 * Created by Marta Turchyniak on 10/5/19.
 */

val useCasesModule = module{
    single{ SimpleCreatePostUseCase(get()) }
}