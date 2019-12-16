package com.magic.Owners.presentation.ui.create_post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.magic.Owners.R

/**
 * Created by Marta Turchyniak on 2019-11-24.
 */
class TagsAdapter(private val tags: ArrayList<String>): RecyclerView.Adapter<TagsAdapter.TagsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        return TagsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tag_buble, parent, false))
    }

    override fun getItemCount(): Int {
        return tags.count()
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        holder.tagTitle.text = tags[position]
    }


    inner class TagsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tagTitle: TextView = itemView.findViewById(R.id.tvTag)
    }
}