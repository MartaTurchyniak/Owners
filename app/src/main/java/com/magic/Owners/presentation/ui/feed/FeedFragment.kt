package com.magic.Owners.presentation.ui.feed

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.magic.Owners.R
import com.magic.Owners.presentation.ui.create_post.CreatePostViewModel
import com.magic.Owners.presentation.ui.main.Status
import com.magic.Owners.presentation.ui.main.ViewModelFragment
import com.magic.Owners.presentation.ui.services.ServicesAdapter
import kotlinx.android.synthetic.main.fragment_feed.*
import org.koin.android.viewmodel.ext.android.viewModel

class FeedFragment : ViewModelFragment<FeedViewModel>() {

    private val feedViewModel: FeedViewModel by viewModel()

    override fun getLayoutID(): Int {
        return R.layout.fragment_feed
    }

    override fun bindViewModel() {
        super.bindViewModel()
        feedViewModel.getFeed()
        feedViewModel.feed().observe(this, Observer {
            if(it.status == Status.SUCCESS){
                val newList = it.data?.asReversed()?.toList()
                val adapter = newList?.let { list -> FeedAdapter(list) }
                rvFeed.adapter = adapter
            } else if(it.status == Status.ERROR){
                showError(it.message)
            }
        })
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

}
