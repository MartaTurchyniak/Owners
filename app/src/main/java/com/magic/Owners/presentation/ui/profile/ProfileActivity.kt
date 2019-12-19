package com.magic.Owners.presentation.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.magic.Owners.R
import com.magic.Owners.presentation.util.GalleryPictureResolver
import com.magic.Owners.presentation.util.Permissions
import com.magic.Owners.presentation.util.permissionsGranted
import com.magic.Owners.presentation.util.requestPermissions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.profile_toolbar.*
import org.koin.android.ext.android.inject
import pub.devrel.easypermissions.AfterPermissionGranted
import java.io.File


/**
 * Created by Marta Turchyniak on 2019-11-24.
 */
class ProfileActivity: AppCompatActivity() {

    private val galleryResolver: GalleryPictureResolver by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        arrow.setOnClickListener {
            onBackPressed()
        }
        addProfilePicture.setOnClickListener {
           takeGalleryPicture()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when {
            galleryResolver.handleActivityResult(baseContext, requestCode, resultCode, data) ->
                galleryResolver.takenPicture()?.let { onGalleryPictureTaken(it) }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    @AfterPermissionGranted(Permissions.REQUEST_CODE_STORAGE)
    private fun takeGalleryPicture() {
        if (permissionsGranted(Permissions.PERMISSIONS_STORAGE)) {
            galleryResolver.takePicture(this)
        } else {
            requestPermissions(
                Permissions.PERMISSIONS_STORAGE,
                Permissions.REQUEST_CODE_STORAGE,
                getString(R.string.general_storage_rationale)
            )
        }
    }

    fun onGalleryPictureTaken(takenPicture: File) {
        Picasso.get().load(takenPicture).into(profile)
    }


    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }
}