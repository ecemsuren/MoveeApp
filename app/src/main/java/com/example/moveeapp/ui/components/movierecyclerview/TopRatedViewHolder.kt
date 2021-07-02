package com.example.moveeapp.ui.components.movierecyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.moveeapp.BuildConfig
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.data.model.tmdb.TvSeries
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.GenericAdapter

/**
 * Created by Ecem Suren on 7.10.2020.
 */
class TopRatedViewHolder(itemView : View, val model: TvSeries): BaseViewHolder(itemView) {

    private val topRatedText = itemView.findViewById(R.id.top_rated_title) as TextView
    private val topRatedImage = itemView.findViewById(R.id.top_rated_tv_img) as ImageView
    private val topRatedVote = itemView.findViewById(R.id.top_rated_vote) as TextView

    override fun bindItems() {

        topRatedText.text = model.name
        topRatedVote.text = model.voteAverage.toString()

        Glide.with(TMDBApi.context)
            .load(BuildConfig.IMAGE_URL + model.posterPath)
            .centerCrop()
            .into(topRatedImage)
        itemView.setOnClickListener {
            GenericAdapter.onItemClick.onItemClick(model)
        }

    }
}