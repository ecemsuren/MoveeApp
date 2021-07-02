package com.example.moveeapp.ui.components.movierecyclerview

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moveeapp.R
import com.example.moveeapp.application.MoveeApplication
import com.example.moveeapp.data.model.tmdb.Movie
import com.example.moveeapp.data.model.tmdb.TvSeries
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseRecyclerviewItem
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.GenericAdapter
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.RecyclerViewSection
import com.example.moveeapp.ui.components.movierecyclerview.util.OnItemClickListener
import com.example.moveeapp.ui.model.CarauselTvSeriesItem
import com.example.moveeapp.ui.model.CarouselMovieItem
import com.example.moveeapp.ui.modules.main.viewModel.MainActivityViewModel

/**
 * Created by Ecem Suren on 29.09.2020.
 */
class RecyclerSectionViewHolder(itemView: View, _type: Int?, val model: RecyclerViewSection, val listener: OnItemClickListener)
    : BaseViewHolder(itemView) {

    private var movieRecyclerView : RecyclerView? = null
    private var type = _type

    override fun bindItems() {
        movieRecyclerView = itemView.findViewById(R.id.vision_recyclerview)
        val mLayoutManager = LinearLayoutManager(MoveeApplication.appContext)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        movieRecyclerView?.layoutManager = mLayoutManager
        val itemList = if (type == 0) {
            getCarouselMovieList(MainActivityViewModel.visionFilm as List<Movie>)
        } else {
            getCarouselTvSeriesList(MainActivityViewModel.popularTvSeries as List<TvSeries>)
        }
        val movieRecyclerViewAdapter = GenericAdapter(listener, itemList as List<BaseRecyclerviewItem>)
        movieRecyclerView?.adapter = movieRecyclerViewAdapter
        itemView.setOnClickListener {
            GenericAdapter.onItemClick.onItemClick(model)
        }
    }

    private fun getCarouselMovieList(visionMovieList: List<Movie>) : List<CarouselMovieItem> {
        val carouselMovieList = arrayListOf<CarouselMovieItem>()
        visionMovieList.forEach{
            carouselMovieList.add(CarouselMovieItem(it))
        }
        return carouselMovieList
    }

    fun getCarouselTvSeriesList(tvSeriesList: List<TvSeries>) : List<CarauselTvSeriesItem> {
        val carouselTvSeriesList = arrayListOf<CarauselTvSeriesItem>()
        tvSeriesList.forEach{
            carouselTvSeriesList.add(CarauselTvSeriesItem(it))
        }
        return carouselTvSeriesList
    }
}

