package com.magic.Owners.presentation.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.magic.Owners.R
import com.magic.Owners.domain.models.FeedResponse
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by Marta Turchyniak on 2019-11-24.
 */
const val BASE_URL = "https://sometext.xyz/"

class FeedAdapter(private val list: List<FeedResponse>) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.feed_cell,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        list[position].userPhotoUrl?.let {
            Picasso.get().load(BASE_URL + list[position].userPhotoUrl)
                .placeholder(R.drawable.placeholder).into(holder.avatar)
        }
        holder.nickname.text = list[position].userName?.replace('"'.toString(), "")?.replace("\\", "")
        list[position].photoUrl?.let {
        Picasso.get().load(BASE_URL + list[position].photoUrl)
            .placeholder(R.drawable.progress_animation).into(holder.contentPhoto)
        }
        holder.tvTitle.text = list[position].title?.replace('"'.toString(), "")?.replace("\\", "")
        holder.tvDescription.text =
            list[position].description.replace('"'.toString(), "").replace("\\", "")
    }


    inner class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar = itemView.findViewById<CircleImageView>(R.id.profile_image)
        val nickname = itemView.findViewById<TextView>(R.id.nickname)
        val contentPhoto = itemView.findViewById<ImageView>(R.id.content)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }
}