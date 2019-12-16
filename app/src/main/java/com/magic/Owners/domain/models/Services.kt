package com.magic.Owners.domain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Marta Turchyniak on 2019-12-15.
 */
data class Services(
    @SerializedName("id")
    val id: Int,
    @SerializedName("tag")
    val tag: String,
    @SerializedName("service_photo_id")
    val photoId: String,
    @SerializedName("photo")
    val detailedPhoto: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("website")
    val website: String
) : Serializable

data class ServiceList(
    @SerializedName("services")
    val services: ArrayList<Services>
)