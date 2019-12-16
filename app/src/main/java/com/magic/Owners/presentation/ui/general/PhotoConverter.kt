package com.magic.Owners.presentation.ui.general

import android.util.Log
import android.widget.ImageView
import com.magic.Owners.R
import com.magic.Owners.presentation.ui.services.ServicesAdapter

/**
 * Created by Marta Turchyniak on 2019-12-16.
 */
class PhotoConverter(
    private val photoId: String,
    private val imageView: ImageView
) {

    fun convert() {
        try {
            val res = R.drawable::class.java
            val field = res.getField(photoId)
            val drawableId = field.getInt(null)
            imageView.setImageResource(drawableId)
        } catch (e: Exception) {
            Log.e("MyTag", "Failure to get drawable id.", e)
        }
    }
}