package com.magic.Owners.presentation.ui.auth.signin

import androidx.lifecycle.MutableLiveData
import com.magic.Owners.domain.models.auth.SignInResponse
import com.magic.Owners.domain.use_cases.AuthUseCase
import com.magic.Owners.presentation.ui.main.DisposableViewModel
import com.magic.Owners.presentation.ui.main.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
class SignInViewModel(private val useCase: AuthUseCase) : DisposableViewModel() {

    private val loginData = MutableLiveData<Resource<SignInResponse>>()

    fun doLogin(email: String, password: String) {
        compositeDisposable.add(
            useCase
                .signIn(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loginData.postValue(Resource.success(it))
                }, {
                    loginData.postValue(Resource.error(it.message))
                })
        )

    }

    fun loginData(): MutableLiveData<Resource<SignInResponse>>{
        return loginData
    }

}