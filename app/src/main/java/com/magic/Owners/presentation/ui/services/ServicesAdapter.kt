package com.magic.Owners.presentation.ui.services

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.magic.Owners.R
import com.magic.Owners.domain.models.ServiceList
import android.util.Log
import com.magic.Owners.presentation.ui.general.PhotoConverter


/**
 * Created by Marta Turchyniak on 2019-11-24.
 */
class ServicesAdapter(private val list: ServiceList): RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder>(){

    private lateinit var onItemClickListener: View.OnClickListener;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        return ServicesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.service_cell, parent, false))

    }

    override fun getItemCount(): Int {
        return list.services.size
    }

    fun setItemClickListener(clickListener: View.OnClickListener) {
        onItemClickListener = clickListener;
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        initPicture(position, holder)
        holder.serviceTitle.text = list.services[position].title
    }


    private fun initPicture(
        position: Int,
        holder: ServicesViewHolder
    ) {
//        PhotoConverter(list.services[position].photoId, holder.serviceIcon)
        try {
            val res = R.drawable::class.java
            val field = res.getField(list.services[position].photoId)
            val drawableId = field.getInt(null)
            holder.serviceIcon.setImageResource(drawableId)
        } catch (e: Exception) {
            Log.e("MyTag", "Failure to get drawable id.", e)
        }
    }

    inner class ServicesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val serviceIcon: ImageView = itemView.findViewById(R.id.ivService)
        val serviceTitle: TextView = itemView.findViewById(R.id.tvService)

        init {
            itemView.tag = this
            itemView.setOnClickListener(onItemClickListener)
        }
    }
}