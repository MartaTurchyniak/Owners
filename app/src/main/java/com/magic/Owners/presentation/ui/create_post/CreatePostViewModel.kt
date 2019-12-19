package com.magic.Owners.presentation.ui.create_post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magic.Owners.domain.models.CreatePost
import com.magic.Owners.domain.models.auth.SignInResponse
import com.magic.Owners.domain.use_cases.SimpleCreatePostUseCase
import com.magic.Owners.presentation.ui.main.DisposableViewModel
import com.magic.Owners.presentation.ui.main.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
class CreatePostViewModel(private val useCase: SimpleCreatePostUseCase): DisposableViewModel() {

    private val createPostData = MutableLiveData<Resource<CreatePost>>()

    fun createPost(image: File, title: String, description: String) {
        compositeDisposable.add(
            useCase
                .create(image, title, description)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    createPostData.postValue(Resource.success(it))
                }, {
                    createPostData.postValue(Resource.error(it.message))
                })
        )

    }

    fun liveData(): MutableLiveData<Resource<CreatePost>> {
        return createPostData
    }
}