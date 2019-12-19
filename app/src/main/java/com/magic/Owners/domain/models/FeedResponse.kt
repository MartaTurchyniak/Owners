package com.magic.Owners.domain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Marta Turchyniak on 2019-12-19.
 */
data class FeedResponse (
    @SerializedName("_id")
    val id: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("userId")
    val userId: String?,
    @SerializedName("photoUrl")
    val photoUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("userPhotoUrl")
    val userPhotoUrl: String?,
    @SerializedName("userName")
    val userName: String?
): Serializable