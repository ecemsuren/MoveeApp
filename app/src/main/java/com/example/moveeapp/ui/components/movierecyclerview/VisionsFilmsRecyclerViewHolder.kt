package com.example.moveeapp.ui.components.movierecyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.moveeapp.BuildConfig
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.ui.base.GenreUtil
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseRecyclerviewItem
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.GenericAdapter
import com.example.moveeapp.ui.model.CarauselTvSeriesItem
import com.example.moveeapp.ui.model.CarouselMovieItem
import com.example.moveeapp.ui.modules.main.viewModel.MainActivityViewModel

/**
 * Created by Ecem Suren on 29.09.2020.
 */

class VisionsFilmsRecyclerViewHolder (itemView : View, val model: BaseRecyclerviewItem): BaseViewHolder(itemView){

    private val visionsMovieName = itemView.findViewById(R.id.visions_movie_txt_title) as TextView
    private val visionGenresName = itemView.findViewById(R.id.visions_txt_genre) as TextView
    private val visionFilmVotes = itemView.findViewById(R.id.visions_txt_vote) as TextView
    private val visionsFilmImage = itemView.findViewById(R.id.visions_img_movie) as ImageView

    override fun bindItems() {

        if (model is CarouselMovieItem){
            initMovieRow(model)
        } else {
            initTvRow(model as CarauselTvSeriesItem)
        }

    }

    fun initMovieRow(t: CarouselMovieItem){
        visionsMovieName.text = t.movie.title
        visionFilmVotes.text = t.movie.voteAverage.toString()
        visionGenresName.text = GenreUtil.getGenres(t.movie.genreIds, MainActivityViewModel.genreList)

        Glide.with(TMDBApi.context)
            .load(BuildConfig.IMAGE_URL + t.movie.posterPath)
            .centerCrop()
            .into(visionsFilmImage)
        itemView.setOnClickListener {
            GenericAdapter.onItemClick.onItemClick(t)
        }
    }

    fun initTvRow(t: CarauselTvSeriesItem){
        visionsMovieName.text = t.tvSeries.name
        visionFilmVotes.text = t.tvSeries.voteAverage.toString()
        visionGenresName.text = GenreUtil.getGenres(t.tvSeries.genreIds, MainActivityViewModel.tvSeriesGenreList)

        Glide.with(TMDBApi.context)
            .load(BuildConfig.IMAGE_URL + t.tvSeries.posterPath)
            .centerCrop()
            .into(visionsFilmImage)
        itemView.setOnClickListener {
            GenericAdapter.onItemClick.onItemClick(t)
        }
    }
}