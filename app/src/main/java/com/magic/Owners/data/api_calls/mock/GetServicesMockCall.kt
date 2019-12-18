package com.magic.Owners.data.api_calls.mock

import com.magic.Owners.domain.api.GetAllServicesCall
import com.magic.Owners.domain.models.ServiceList
import com.magic.Owners.data.json_parser.JsonParser
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import android.content.Context
import com.google.gson.Gson
import java.util.concurrent.TimeUnit

/**
 * Created by Marta Turchyniak on 2019-12-15.
 */
class GetServicesMockCall(val context: Context, val gson: Gson): GetAllServicesCall {

    override fun getAllServices(): Single<ServiceList> {
        val s: String? = JsonParser("services_sample", context).text()
        val services = gson.fromJson(s, ServiceList::class.java)

        return Single.timer(200, TimeUnit.MILLISECONDS)
            .map {services}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}