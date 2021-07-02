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
import com.example.moveeapp.ui.model.FavoriteMovieItem

/**
 * Created by Ecem Suren on 3.11.2020.
 */
class FavoriteMovieRecyclerViewHolder (itemView : View, val model: FavoriteMovieItem): BaseViewHolder(itemView) {

    val profileFilmImage = itemView.findViewById<ImageView>(R.id.recyclerview_img_profile)
    val profileFilmName = itemView.findViewById<TextView>(R.id.recyclerview_txt_profile_movie_name)
    val profileFilmInformation = itemView.findViewById<TextView>(R.id.recyclerview_txt_profile_information)
    val profileFilmDate = itemView.findViewById<TextView>(R.id.recyclerview_txt_profile_date)

    override fun bindItems() {

        profileFilmName.text = model.favoriteMovie.title
        profileFilmDate.text = model.favoriteMovie.releaseDate

        Glide.with(TMDBApi.context)
            .load(BuildConfig.IMAGE_URL + model.favoriteMovie.posterPath)
            .placeholder(R.drawable.ic_no_image)
            .into(profileFilmImage)

        itemView.setOnClickListener {
            GenericAdapter.onItemClick.onItemClick(model)
        }

    }

}