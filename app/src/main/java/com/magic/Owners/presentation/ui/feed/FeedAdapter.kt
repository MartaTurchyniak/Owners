package com.magic.Owners.presentation.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.magic.Owners.R

/**
 * Created by Marta Turchyniak on 2019-11-24.
 */
class FeedAdapter(): RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.feed_cell, parent, false))
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {

    }


    inner class FeedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}