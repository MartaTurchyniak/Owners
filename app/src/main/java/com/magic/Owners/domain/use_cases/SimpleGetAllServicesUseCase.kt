package com.magic.Owners.domain.use_cases

import com.magic.Owners.domain.api.GetAllServicesCall
import com.magic.Owners.domain.models.ServiceList
import com.magic.Owners.domain.models.Services
import io.reactivex.Single

/**
 * Created by Marta Turchyniak on 2019-12-15.
 */
class SimpleGetAllServicesUseCase(private val useCase: GetAllServicesCall) {

    fun getAllServicesUseCase(): Single<ServiceList> {
        return useCase.getAllServices()
    }
}