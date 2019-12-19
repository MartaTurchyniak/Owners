package com.magic.Owners.presentation.ui.services

import androidx.lifecycle.MutableLiveData
import com.magic.Owners.domain.models.ServiceList
import com.magic.Owners.domain.use_cases.SimpleGetAllServicesUseCase
import com.magic.Owners.presentation.ui.main.DisposableViewModel
import com.magic.Owners.presentation.ui.main.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Marta Turchyniak on 2019-12-15.
 */
class ServicesViewModel(private val getAllServices: SimpleGetAllServicesUseCase): DisposableViewModel() {

    private val serviceList = MutableLiveData<Resource<ServiceList>>()

    fun bind(){
        loadServices()
    }

    private fun loadServices() {
        compositeDisposable.add(
            getAllServices.getAllServicesUseCase().subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe({
                showServices(it)
            }, {
                it.printStackTrace()
            })
        )
    }

    private fun showServices(services: ServiceList) {
        this.serviceList.postValue(Resource.success(services))
    }

    fun serviceList(): MutableLiveData<Resource<ServiceList>>{
        return serviceList
    }
}