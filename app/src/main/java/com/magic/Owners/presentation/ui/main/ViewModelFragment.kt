package com.magic.Owners.presentation.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

/**
 * Created by Marta Turchyniak on 2019-12-15.
 */

abstract class ViewModelFragment<T : DisposableViewModel> : Fragment() {

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        bindViewModel()
    }

    final override fun onCreateView(inflater: LayoutInflater,
                                    container: ViewGroup?,
                                    savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutID(), container, false)
    }

    protected open fun setupUI() {}

    protected open fun bindViewModel() {}

    @LayoutRes
    protected abstract fun getLayoutID(): Int

}