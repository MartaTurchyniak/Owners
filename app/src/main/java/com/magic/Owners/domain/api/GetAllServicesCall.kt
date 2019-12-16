package com.magic.Owners.domain.api

import com.magic.Owners.domain.models.ServiceList
import com.magic.Owners.domain.models.Services
import io.reactivex.Single

/**
 * Created by Marta Turchyniak on 2019-12-15.
 */
interface GetAllServicesCall {

   fun getAllServices(): Single<ServiceList>
}