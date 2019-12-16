package com.magic.Owners.presentation.ui.services

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.magic.Owners.R
import com.magic.Owners.presentation.ui.main.ViewModelFragment
import com.magic.Owners.presentation.ui.services.service_details.ServiceDetailsActivity
import kotlinx.android.synthetic.main.fragment_services.*
import org.koin.android.viewmodel.ext.android.viewModel

class ServicesFragment : ViewModelFragment<ServicesViewModel>() {
    private var listener: OnFragmentInteractionListener? = null
    private val viewModel: ServicesViewModel by viewModel()

    override fun getLayoutID(): Int {
        return R.layout.fragment_services
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.bind()
        viewModel.serviceList().observe(this, Observer {
            list ->
            gridView.layoutManager = GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)
            val adapter = list.data?.let { ServicesAdapter(it) }
            gridView.adapter = adapter
            adapter?.setItemClickListener(onItemClickListener)
        })
    }

    private val onItemClickListener = View.OnClickListener { view ->
        val viewHolder = view.tag as RecyclerView.ViewHolder
        val position = viewHolder.adapterPosition
        val thisItem = viewModel.serviceList().value?.data?.services?.get(position)
        context?.let { thisItem?.let { service ->
            ServiceDetailsActivity.startServiceDetailsActivity(it,
                service
            )
        } }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}
