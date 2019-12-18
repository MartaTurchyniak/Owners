package com.magic.Owners.presentation.ui.auth.signup

import androidx.lifecycle.MutableLiveData
import com.magic.Owners.domain.models.auth.SignUpResponse
import com.magic.Owners.domain.use_cases.impl.AuthUseCase
import com.magic.Owners.presentation.ui.main.DisposableViewModel
import com.magic.Owners.presentation.ui.main.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
class SignUpViewModel(private val useCase: AuthUseCase) : DisposableViewModel() {

    private val signUpData = MutableLiveData<Resource<SignUpResponse>>()

    fun doSignUp(email: String, password: String, phone: String) {
        compositeDisposable.add(
            useCase
                .signUp(email, password, phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    signUpData.postValue(Resource.success(it))
                }, {
                    signUpData.postValue(Resource.error(it.message))
                })
        )

    }

    fun signUpData(): MutableLiveData<Resource<SignUpResponse>> {
        return signUpData
    }

}