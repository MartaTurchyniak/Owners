package com.magic.Owners.domain.models.auth.body

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
data class AuthSignIn (
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
): Serializable