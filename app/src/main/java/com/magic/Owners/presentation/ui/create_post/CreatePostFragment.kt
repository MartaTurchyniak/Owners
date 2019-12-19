package com.magic.Owners.presentation.ui.create_post

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.magic.Owners.R
import com.magic.Owners.presentation.ui.main.Status
import com.magic.Owners.presentation.ui.main.ViewModelFragment
import com.magic.Owners.presentation.util.GalleryPictureResolver
import com.magic.Owners.presentation.util.Permissions
import com.magic.Owners.presentation.util.permissionsGranted
import com.magic.Owners.presentation.util.requestPermissions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_create_post.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import pub.devrel.easypermissions.AfterPermissionGranted
import java.io.File

class CreatePostFragment : ViewModelFragment<CreatePostViewModel>(){

    private val createPostViewModel: CreatePostViewModel by viewModel()
    private val galleryResolver: GalleryPictureResolver by inject()
    private var photoFile: File? = null

    override fun getLayoutID(): Int {
        return R.layout.fragment_create_post
    }

    override fun setupUI() {
        super.setupUI()
        ivAdd.setOnClickListener {
            takeGalleryPicture()
        }
        addPost.setOnClickListener {
            photoFile?.let { file ->
                createPostViewModel.createPost(file,
                    etTitle.text.toString(),
                    etDescription.text.toString())
            }
        }
    }

    override fun bindViewModel() {
        super.bindViewModel()
        createPostViewModel.liveData().observe( this, Observer {
            if(it.status == Status.SUCCESS){
                addPost.findNavController().navigate(R.id.feedFragment)
            } else if(it.status == Status.ERROR) {
                showError(it.message)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        context?.let {
        when {
            galleryResolver.handleActivityResult(it, requestCode, resultCode, data) ->
                galleryResolver.takenPicture()?.let {picture -> onGalleryPictureTaken(picture) }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
        }
    }

    @AfterPermissionGranted(Permissions.REQUEST_CODE_STORAGE)
    private fun takeGalleryPicture() {
        activity?.let {
            if (it.permissionsGranted(Permissions.PERMISSIONS_STORAGE)) {
                galleryResolver.takePicture(this)
            } else {
                requestPermissions(
                    Permissions.PERMISSIONS_STORAGE,
                    Permissions.REQUEST_CODE_STORAGE,
                    getString(R.string.general_storage_rationale)
                )
            }
        }
    }

    fun onGalleryPictureTaken(takenPicture: File) {
        plus.visibility = View.GONE
        Picasso.get().load(takenPicture).placeholder(R.drawable.buble_background).into(ivAdd)
        photoFile = takenPicture
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}
