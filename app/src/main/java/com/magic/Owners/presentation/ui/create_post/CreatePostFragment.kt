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
        initPostBtn(true)
        ivAdd.setOnClickListener {
            takeGalleryPicture()
        }
        addPost.setOnClickListener {
            if(photoFile != null && etTitle.text.isNotEmpty()){
                initPostBtn(false)
                photoFile?.let {
                    createPostViewModel.createPost(
                        it,
                        etTitle.text.toString(),
                        etDescription.text.toString()
                    )
                }
            }
        }
    }

    fun initPostBtn(isEnabled: Boolean){
        addPost.isEnabled = isEnabled
        if(isEnabled) {
            addPost.setBackgroundDrawable(resources.getDrawable(R.drawable.signin_btn_bg))
            addPost.text = "Post"
        } else{
            addPost.setBackgroundDrawable(resources.getDrawable(R.drawable.add_post_btn_dis))
            addPost.text = "Posting..."
        }
    }
    override fun bindViewModel() {
        super.bindViewModel()
        createPostViewModel.liveData().observe( this, Observer {
            if(it.status == Status.SUCCESS){
                addPost.findNavController().navigate(R.id.feedFragment)
                initPostBtn(true)
            } else if(it.status == Status.ERROR) {
                showError(it.message)
                initPostBtn(true)
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
        Picasso.get().load(takenPicture).placeholder(R.drawable.buble_background).fit()
            .into(ivAdd)
        photoFile = takenPicture
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}
