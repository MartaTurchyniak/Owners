package com.magic.Owners.presentation.ui.profile

import androidx.lifecycle.MutableLiveData
import com.magic.Owners.domain.models.FeedResponse
import com.magic.Owners.domain.models.User
import com.magic.Owners.domain.repository.user.UserRepository
import com.magic.Owners.domain.use_cases.UserUseCase
import com.magic.Owners.presentation.ui.main.DisposableViewModel
import com.magic.Owners.presentation.ui.main.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

/**
 * Created by Marta Turchyniak on 2019-12-19.
 */
class ProfileViewModel(private val userUseCase: UserUseCase,
                       private val userRepository: UserRepository): DisposableViewModel() {


    private val user = MutableLiveData<Resource<User>>()
    private val updatedUser = MutableLiveData<Resource<User>>()

    fun getUser() {
        compositeDisposable.add(
            userUseCase.getUserData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    user.postValue(Resource.success(it))
                }, {
                    user.postValue(Resource.error(it.message))
                })
        )

    }

    fun updateUserData(name: String, phone: String, image: File){
        compositeDisposable.add(
            userUseCase.updateUserData(name, phone, image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    updatedUser.postValue(Resource.success(it))
                }, {
                    updatedUser.postValue(Resource.error(it.message))
                })
        )
    }

    fun user(): MutableLiveData<Resource<User>> {
        return user
    }

    fun updatedUser(): MutableLiveData<Resource<User>> {
        return updatedUser
    }

    fun userRepo(): User{
        return userRepository.getUser()
    }
}