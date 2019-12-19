package com.magic.Owners.domain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
data class CreatePost(
    @SerializedName("id")
    val id: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("photoUrl")
    val photoUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String
): Serializable