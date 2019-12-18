package com.magic.Owners.domain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
data class User (
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("photoUrl")
    var photoUrl: String?,
    @SerializedName("createdAt")
    val createdAt: String
): Serializable