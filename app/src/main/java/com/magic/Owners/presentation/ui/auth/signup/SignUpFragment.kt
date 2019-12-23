package com.magic.Owners.presentation.ui.auth.signup

import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.magic.Owners.R
import com.magic.Owners.presentation.ui.main.Status
import com.magic.Owners.presentation.ui.main.ViewModelFragment
import kotlinx.android.synthetic.main.sign_up_frg.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Marta Turchyniak on 2019-12-16.
 */
class SignUpFragment: ViewModelFragment<SignUpViewModel>() {

    private val signUpViewModel: SignUpViewModel by viewModel()

    override fun getLayoutID(): Int {
        return R.layout.sign_up_frg
    }

    override fun bindViewModel() {
        super.bindViewModel()
        signUpViewModel.signUpData().observe(this, Observer {
            if(it.status == Status.ERROR){
                showError(it.message)
            } else if(it.status == Status.SUCCESS){
                btnSignup.findNavController().navigate(R.id.mainActivity)
            }
        })

    }

    override fun setupUI() {
        super.setupUI()
        initSignupClick()
        navigate_signin.setOnClickListener {
            it.findNavController().navigate(R.id.signInFragment)
        }
    }

    private fun initSignupClick() {
        btnSignup.setOnClickListener {
            if (etUsername.text.toString().isNotEmpty()
                && etPhoneNumber.text.toString().isNotEmpty()
                && etPassword.text.toString().isNotEmpty()
                && etConirmPassword.text.toString().isNotEmpty()
                && etPassword.text.toString() == etConirmPassword.text.toString()
            ) {
                signUpViewModel.doSignUp(
                    etUsername.text.toString(),
                    etPassword.text.toString(),
                    etPhoneNumber.text.toString()
                )
            } else if (etPassword.text.toString() == etConirmPassword.text.toString()) {
                showError("Password is not match")
            } else {
                showError("Some fields are empty")
            }
        }
    }

}