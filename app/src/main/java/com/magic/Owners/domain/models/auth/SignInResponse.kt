package com.magic.Owners.domain.models.auth

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
data class SignInResponse (
    @SerializedName("token")
    val token: String
): Serializable