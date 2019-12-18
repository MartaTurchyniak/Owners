package com.magic.Owners.presentation.ui.auth.signin

import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.magic.Owners.R
import com.magic.Owners.presentation.ui.main.Status
import com.magic.Owners.presentation.ui.main.ViewModelFragment
import kotlinx.android.synthetic.main.login_frg.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Marta Turchyniak on 2019-12-01.
 */
class SignInFragment() : ViewModelFragment<SignInViewModel>(){

    private val signInViewModel: SignInViewModel by viewModel()

    override fun getLayoutID(): Int {
        return R.layout.login_frg
    }

    override fun setupUI() {
        super.setupUI()
        btnSignin.setOnClickListener {
            if (etPassword.text.toString().isNotEmpty() && etUsername.text.toString().isNotEmpty()) {
                signInViewModel.doLogin(etUsername.text.toString(), etPassword.text.toString())
            } else if(etPassword.text.toString().isEmpty()){
                etPassword.hint = "This field is required"
                etPassword.setHintTextColor(resources.getColor(R.color.colorAccent))
            } else if(etUsername.text.toString().isEmpty()){
                etUsername.hint = "This field is required"
                etUsername.setHintTextColor(resources.getColor(R.color.colorAccent))
            }
        }
        initNavigation()
    }

    override fun bindViewModel() {
        super.bindViewModel()
        signInViewModel.loginData().observe(this, Observer {
           if (it.status == Status.ERROR) {
               showError(it.message)
           } else if(it.status ==  Status.SUCCESS){
               it.data?.let {
                    btnSignin.findNavController().navigate(R.id.mainActivity) }
            }
        })
    }

    private fun initNavigation() {
        navigate_signup.setOnClickListener {
            it.findNavController().navigate(R.id.signUpFragment)
        }
    }

}