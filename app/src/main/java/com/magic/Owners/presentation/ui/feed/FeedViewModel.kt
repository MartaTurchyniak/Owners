package com.magic.Owners.presentation.ui.feed

import androidx.lifecycle.MutableLiveData
import com.magic.Owners.domain.models.FeedResponse
import com.magic.Owners.domain.models.auth.SignInResponse
import com.magic.Owners.domain.use_cases.FeedUseCase
import com.magic.Owners.presentation.ui.main.DisposableViewModel
import com.magic.Owners.presentation.ui.main.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Marta Turchyniak on 2019-12-19.
 */
class FeedViewModel(private val useCase: FeedUseCase): DisposableViewModel() {

    private val feedData = MutableLiveData<Resource<ArrayList<FeedResponse>>>()

    fun getFeed() {
        compositeDisposable.add(
            useCase
                .getFeed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    feedData.postValue(Resource.success(it))
                }, {
                    feedData.postValue(Resource.error(it.message))
                })
        )

    }

    fun feed(): MutableLiveData<Resource<ArrayList<FeedResponse>>> {
        return feedData
    }
}