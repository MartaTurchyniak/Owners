package com.magic.Owners.presentation.util

import android.app.Activity
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import java.io.File
import android.content.Context
import android.content.Intent
import java.util.*

/**
 * Created by Marta Turchyniak on 2019-12-18.
 */
class GalleryPictureResolver(private val cacheStorage: CasheStorage) {

    private var takenPicture: File? = null

    fun takePicture(activity: Activity) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        activity.startActivityForResult(intent, REQUEST_IMAGES_FROM_GALLERY)
    }

    fun takePicture(fragment: Fragment) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        fragment.startActivityForResult(intent, REQUEST_IMAGES_FROM_GALLERY)
    }

    fun handleActivityResult(
        context: Context,
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ): Boolean {
        return if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_IMAGES_FROM_GALLERY) {
            data?.data?.let { uri ->
                context.contentResolver.query(
                    uri,
                    arrayOf(MediaStore.Images.Media.DATA),
                    null,
                    null,
                    null
                )?.use { cursor ->
                    cursor.moveToFirst()
                    this.copyToCacheStorage(File(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))))
                }
            }
            true
        } else {
            false
        }
    }

    private fun copyToCacheStorage(file: File) {
        val takenPicture = cacheStorage.newFile("${Date().time}.jpg")
        file.copyTo(takenPicture)

        this.takenPicture = takenPicture
    }


    fun takenPicture(): File? {
        return this.takenPicture
    }

    companion object {
        private const val REQUEST_IMAGES_FROM_GALLERY = 321
    }
}