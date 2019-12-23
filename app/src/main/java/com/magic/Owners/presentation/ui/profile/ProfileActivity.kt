package com.magic.Owners.presentation.ui.profile

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import com.magic.Owners.R
import com.magic.Owners.domain.models.User
import com.magic.Owners.presentation.ui.feed.FeedViewModel
import com.magic.Owners.presentation.ui.main.Resource
import com.magic.Owners.presentation.ui.main.Status
import com.magic.Owners.presentation.ui.main.ViewModelActivity
import com.magic.Owners.presentation.util.GalleryPictureResolver
import com.magic.Owners.presentation.util.Permissions
import com.magic.Owners.presentation.util.permissionsGranted
import com.magic.Owners.presentation.util.requestPermissions
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.profile_toolbar.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import pub.devrel.easypermissions.AfterPermissionGranted
import java.io.File
import java.lang.Exception


/**
 * Created by Marta Turchyniak on 2019-11-24.
 */
const val BASE_URL = "https://sometext.xyz/"
class ProfileActivity: ViewModelActivity<ProfileViewModel>() {

    private val profileViewModel: ProfileViewModel by viewModel()

    private var image: File ?= null

    override fun getLayoutID(): Int {
        return R.layout.activity_profile
    }

    private val galleryResolver: GalleryPictureResolver by inject()

    override fun bindViewModel() {
        super.bindViewModel()
        profileViewModel.getUser()
        profileViewModel.user().observe(this, Observer {
            if(it.status == Status.SUCCESS){
                it.data?.let { user -> initUser(user) }
            } else if(it.status == Status.ERROR){
                showError(it.message)
//                initUser(profileViewModel.userRepo())
            }
        })
        profileViewModel.updatedUser().observe(this, Observer {
            if(it.status == Status.SUCCESS){
                initSaveBtn(false)
                onBackPressed()
            } else if(it.status == Status.ERROR){
                showError(it.message)
            }
        })

    }

    private fun initUser(user: User) {
        tvTitleDescription.isEnabled = false

        user.name?.let {
            tvTitleDescription.setText(it.replace ('"'.toString(), "").replace("\\", ""))
        }
        tvPhoneDescription.isEnabled = false
        user.phone?.let {
        tvPhoneDescription.setText(it.replace('"'.toString(), "").replace("\\", ""))
        }
        user.email?.let {
            tvEmailDescription.setText(it.replace ('"'.toString(), "").replace("\\", ""))
        }
        if(user.photoUrl != null) {
            Picasso.get().load(BASE_URL + user.photoUrl).placeholder(R.drawable.placeholder)
                .into(profile)
        }
    }

    override fun setupUI() {
        super.setupUI()
        initSaveBtn(false)
        arrow.setOnClickListener {
            onBackPressed()
        }
        addProfilePicture.setOnClickListener {
            takeGalleryPicture()
        }
        ivEditName.setOnClickListener {
            tvTitleDescription.isEnabled = true
            initSaveBtn(true)
        }
        ivEditPhone.setOnClickListener {
            tvPhoneDescription.isEnabled = true
            initSaveBtn(true)
        }
        initUpdateUserData()
    }

    private fun initUpdateUserData() {
        tvSaveChanges.setOnClickListener {
            if (tvTitleDescription.text.toString().isEmpty() ||
                tvPhoneDescription.text.toString().isEmpty()
            ) {
                showError("Some fields are empty")
            } else {
                initSaveBtn(false)
                image?.let { image ->
                    profileViewModel.updateUserData(
                        tvTitleDescription.text.toString(),
                        tvPhoneDescription.text.toString(),
                        image
                    )
                }?:run {
                    showError("Photo is required")
                }
            }
        }
    }

    private fun initSaveBtn(isEnabled: Boolean) {
        tvSaveChanges.isEnabled = isEnabled
        if(isEnabled) {
            tvSaveChanges.setTextColor(resources.getColor(R.color.colorPrimary))
        } else{
            tvSaveChanges.setTextColor(resources.getColor(R.color.dark_background))
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

    private fun onGalleryPictureTaken(takenPicture: File) {
       initSaveBtn(true)
        Picasso.get().load(takenPicture).fit().placeholder(android.R.color.darker_gray).into(profile)
        image = takenPicture
    }


    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }
}