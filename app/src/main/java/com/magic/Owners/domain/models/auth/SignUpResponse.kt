package com.magic.Owners.domain.models.auth

import com.google.gson.annotations.SerializedName
import com.magic.Owners.domain.models.User
import java.io.Serializable

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
data class SignUpResponse (
    @SerializedName("user")
    val user: User,
    @SerializedName("token")
    val token: String
): Serializable