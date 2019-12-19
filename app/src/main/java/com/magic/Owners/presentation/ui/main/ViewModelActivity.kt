package com.magic.Owners.presentation.ui.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.magic.Owners.presentation.ui.general.DialogsManager

/**
 * Created by Marta Turchyniak on 2019-12-19.
 */
abstract class ViewModelActivity<T : DisposableViewModel> : AppCompatActivity() {

    protected val dialogsManager = DialogsManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        setupUI()
        bindViewModel()
    }

    protected open fun setupUI() {}

    protected open fun bindViewModel() {}

    @LayoutRes
    protected abstract fun getLayoutID(): Int

    protected fun showError(message: String?) {
        dialogsManager.showErrorDialog(message)
    }
}