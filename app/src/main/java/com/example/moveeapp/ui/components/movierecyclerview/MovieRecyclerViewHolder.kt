package com.example.moveeapp.ui.components.movierecyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.moveeapp.BuildConfig
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.data.model.tmdb.Movie
import com.example.moveeapp.ui.base.GenreUtil
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.GenericAdapter
import com.example.moveeapp.ui.components.movierecyclerview.util.FavoriteUtil
import com.example.moveeapp.ui.modules.main.viewModel.MainActivityViewModel


/**
 * Created by Ecem Suren on 23.09.2020.
 */

class MovieRecyclerViewHolder(itemView: View, val model: Movie) : BaseViewHolder(itemView)  {

    private val movieName = itemView.findViewById(R.id.tvseries_text) as TextView
    private val genreName = itemView.findViewById(R.id.recyclerview_txt_search_genre_and_films) as TextView
    private val image = itemView.findViewById(R.id.tv_series_image) as ImageView
    private val date = itemView.findViewById(R.id.activity_recyclerview_txt_date) as TextView
    private val vote = itemView.findViewById(R.id.activity_recyclerview_txt_vote) as TextView
    private val like = itemView.findViewById<ImageView>(R.id.movie_img_like)

    override fun bindItems() {

        movieName.text = model.title
        genreName.text = GenreUtil.getGenres(model.genreIds, MainActivityViewModel.genreList)

        Glide.with(TMDBApi.context)
            .load(BuildConfig.IMAGE_URL + model.posterPath)
            .centerCrop()
            .into(image)

        date.text = model.releaseDate
        vote.text = model.voteAverage.toString()
        itemView.setOnClickListener {
            GenericAdapter.onItemClick.onItemClick(model)
        }

        like.visibility = FavoriteUtil.checkFavoriteVisibility(model.id)

    }

}