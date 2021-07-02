package com.example.moveeapp.ui.components.movierecyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.moveeapp.BuildConfig
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.GenericAdapter
import com.example.moveeapp.ui.model.CastMovieItem

/**
 * Created by Ecem Suren on 13.10.2020.
 */
class MoviesOfCastRecyclerViewHolder (itemView : View, val model: CastMovieItem): BaseViewHolder(itemView){

    val  peopleOfMoviesImage = itemView.findViewById<ImageView>(R.id.cast_details_img_people_movie)
    val  peopleOfMoviesText = itemView.findViewById<TextView>(R.id.cast_details_txt_people_movie)

    override fun bindItems() {

        peopleOfMoviesText.text = model.movie.title
        Glide.with(TMDBApi.context)
            .load(BuildConfig.IMAGE_URL + model.movie.posterPath)
            .placeholder(R.drawable.ic_no_image)
            .centerCrop()
            .into(peopleOfMoviesImage)

        itemView.setOnClickListener {
            GenericAdapter.onItemClick.onItemClick(model)
        }

    }

}