package com.magic.Owners.presentation.ui.main

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Marta Turchyniak on 2019-12-15.
 */
open class DisposableViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}