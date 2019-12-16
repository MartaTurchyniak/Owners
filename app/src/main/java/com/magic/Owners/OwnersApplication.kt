package com.magic.Owners

import android.app.Application
import com.magic.Owners.data.di.apiCallsModule
import com.magic.Owners.data.di.networkModule
import com.magic.Owners.domain.di.useCasesModule
import com.magic.Owners.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Marta Turchyniak on 10/5/19.
 */
class OwnersApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@OwnersApplication)
            modules(arrayListOf(networkModule,
                apiCallsModule,
                presentationModule,
                useCasesModule
                )
            )
        }
    }
}