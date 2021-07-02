package com.example.moveeapp.ui.components.movierecyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.moveeapp.BuildConfig
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.data.model.tmdb.KnownFor
import com.example.moveeapp.data.model.tmdb.Search
import com.example.moveeapp.ui.base.GenreUtil
import com.example.moveeapp.ui.base.UIConstants
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.GenericAdapter
import com.example.moveeapp.ui.modules.main.viewModel.MainActivityViewModel

/**
 * Created by Ecem Suren on 20.10.2020.
 */
class SearchRecyclerViewHolder(itemView : View, private val searchModel: Search?, private val knownForModel: KnownFor? ): BaseViewHolder(itemView) {

    val searchImage = itemView.findViewById<ImageView>(R.id.recyclerview_img_search)
    val searchText = itemView.findViewById<TextView>(R.id.recyclerview_txt_search_name)
    val searchGenreAndFilms = itemView.findViewById<TextView>(R.id.recyclerview_txt_search_genre_and_films)
    val searchType = itemView.findViewById<TextView>(R.id.recyclerview_txt_type)
    val searchTypeIcon = itemView.findViewById<ImageView>(R.id.recyclerview_img_type)


    override fun bindItems() {
         searchModel?.let {
             initSearch(it)
        }
        knownForModel?.let{
            initKnownFor(it)

        }

        itemView.setOnClickListener {
            if (searchModel != null) {
                GenericAdapter.onItemClick.onItemClick(searchModel) }
        }
    }

    private fun initSearch (search: Search){
        when (search.mediaType) {
            MediaType.MOVIE.type -> {
                searchText.text = search.title

                Glide.with(TMDBApi.context)
                    .load(BuildConfig.IMAGE_URL + search.posterPath)
                    .placeholder(R.drawable.ic_no_image)
                    .into(searchImage)

                searchType.text = search.mediaType.toUpperCase()
                searchTypeIcon.setImageResource(R.drawable.ic_icon_search_film)
                searchGenreAndFilms.text = GenreUtil.getGenres(search.genreIds, MainActivityViewModel.genreList)
            }
            MediaType.TV.type -> {
                searchText.text = search.name

                Glide.with(TMDBApi.context)
                    .load(BuildConfig.IMAGE_URL + search.posterPath)
                    .placeholder(R.drawable.ic_no_image)
                    .into(searchImage)

                searchType.text = search.mediaType.toUpperCase()
                searchTypeIcon.setImageResource(R.drawable.ic_icon_search_tv)
                searchGenreAndFilms.text = GenreUtil.getGenres(search.genreIds, MainActivityViewModel.genreList)
            }
            else -> {
                searchText.text = search.name

                Glide.with(TMDBApi.context)
                    .load(BuildConfig.IMAGE_URL + search.profilePath)
                    .placeholder(R.drawable.ic_no_profile)
                    .into(searchImage)

                searchType.text = search.mediaType.toUpperCase()
                searchTypeIcon.setImageResource(R.drawable.ic_icon_search_user)
                var playedMovies = UIConstants.EMPTY_STRING
                val knownList = search.knownFor.map { it.title }
                knownList.forEach { title ->
                    if (!title.isNullOrBlank()) {
                        playedMovies += "$title, "
                    }
                }
                searchGenreAndFilms.text = playedMovies
            }

        }
    }

    private fun initKnownFor(itemView: KnownFor){

        searchText.text = itemView.name

        Glide.with(TMDBApi.context)
            .load(BuildConfig.IMAGE_URL + itemView.posterPath)
            .into(searchImage)
    }
}

