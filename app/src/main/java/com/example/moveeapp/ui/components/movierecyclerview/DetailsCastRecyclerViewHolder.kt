package com.example.moveeapp.ui.components.movierecyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.moveeapp.BuildConfig
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.data.model.tmdb.Cast
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.GenericAdapter

/**
 * Created by Ecem Suren on 10.10.2020.
 */
class DetailsCastRecyclerViewHolder (itemView : View, val model: Cast): BaseViewHolder(itemView) {

    val castImage = itemView.findViewById<ImageView>(R.id.details_cast_img)
    val castName = itemView.findViewById<TextView>(R.id.details_cast_txt_name)

    override fun bindItems() {

        castName.text = model.name

        Glide.with(TMDBApi.context)
            .load(BuildConfig.IMAGE_URL + model.profilePath)
            .placeholder(R.drawable.ic_no_profile)
            .circleCrop()
            .into(castImage)

        itemView.setOnClickListener {
            GenericAdapter.onItemClick.onItemClick(model)
        }

    }

}


