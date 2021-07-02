package com.example.moveeapp.ui.modules.main.viewModel

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.data.components.network.repository.tmdb.model.*
import com.example.moveeapp.data.model.tmdb.Genre
import com.example.moveeapp.ui.base.UIConstants
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.*
import com.example.moveeapp.ui.components.movierecyclerview.util.OnItemClickListener
import com.example.moveeapp.ui.modules.main.base.MainBaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ecem Suren on 23.09.2020.
 */

class MainActivityViewModel : MainBaseViewModel() {



    val movieListResponse: MutableLiveData<MovieResponse> = MutableLiveData()
    val genreListResponse: MutableLiveData<GenreListResponse> = MutableLiveData()
    val visionsFilmResponse: MutableLiveData<VisionsMovieResponse> = MutableLiveData()
    val tvSeriesResponse :  MutableLiveData<TvSeriesResponse> = MutableLiveData()
    val topRatedTvResponse: MutableLiveData<TopRatedTvResponse> = MutableLiveData()
    val tvSeriesGenreListResponse: MutableLiveData<TvSeriesGenreResponse> = MutableLiveData()


    private var sectionList: List<BaseHeaderModel> = ArrayList()

    companion object{

        lateinit var genreList: List<Genre>
        lateinit var visionFilm: List<BaseRecyclerviewItem>
        lateinit var tvSeriesGenreList: List<Genre>
        lateinit var popularTvSeries : List <BaseRecyclerviewItem>

    }

    fun getSectionList(header1: String, header2: String, type: Int): List<BaseHeaderModel> {

        if(type == UIConstants.ZERO) {
            (sectionList as ArrayList).add(FirstHeader(UIConstants.HEADER_MOVIE_POSITION, header1, type))
            (sectionList as ArrayList).add(SecondHeader(UIConstants.SECTION_POPULAR_POSITION, header2, type))

        }else {

            (sectionList as ArrayList).add(FirstHeader(UIConstants.ZERO, header1, UIConstants.TWO))
            (sectionList as ArrayList).add(SecondHeader(UIConstants.TWO, header2, UIConstants.TWO))

        }

        return sectionList
    }

    fun getRecyclerList(listener: OnItemClickListener, type: Int): List<RecyclerViewSection> {
        return ArrayList<RecyclerViewSection>().apply {
            add(RecyclerViewSection(UIConstants.VISION_RECYCLERVIEW_POSITION, UIConstants.VISION_RECYCLERVIEW, type, listener))
        }
    }

    private var populerFilmCallBack = object : Callback<MovieResponse> {
        override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
            if (response.isSuccessful) {
                movieListResponse.value = response.body()
            } else{
                errorMessage.value = Resources.getSystem().getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            errorMessage.value = Resources.getSystem().getString(R.string.failed)
        }
    }

    private var genreListCallback = object : Callback<GenreListResponse> {
        override fun onResponse(
            call: Call<GenreListResponse>,
            response: Response<GenreListResponse>
        ) {
            if (response.isSuccessful) {
                genreListResponse.value = response.body()
            }else{
                errorMessage.value = Resources.getSystem().getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<GenreListResponse>, t: Throwable) {
            errorMessage.value = Resources.getSystem().getString(R.string.failed)
        }
    }

    private var visionsMovieListCallback = object : Callback<VisionsMovieResponse> {
        override fun onResponse(
            call: Call<VisionsMovieResponse>,
            response: Response<VisionsMovieResponse>
        ) {
            if (response.isSuccessful) {
                visionsFilmResponse.value = response.body()
            }else{
                errorMessage.value = Resources.getSystem().getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<VisionsMovieResponse>, t: Throwable) {
            errorMessage.value = Resources.getSystem().getString(R.string.failed)
        }
    }
    private var topRatedTvCallBack = object : Callback<TopRatedTvResponse> {

        override fun onResponse(call: Call<TopRatedTvResponse>, response: Response<TopRatedTvResponse>) {
            if (response.isSuccessful) {
                topRatedTvResponse.value = response.body()
            } else{
                errorMessage.value = Resources.getSystem().getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<TopRatedTvResponse>, t: Throwable) {
            errorMessage.value = Resources.getSystem().getString(R.string.failed)
        }
    }

    private var tvSeriesCallBack = object : Callback<TvSeriesResponse> {

        override fun onResponse(call: Call<TvSeriesResponse>, response: Response<TvSeriesResponse>) {
            if (response.isSuccessful) {
                tvSeriesResponse.value = response.body()
            } else{
                errorMessage.value = Resources.getSystem().getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<TvSeriesResponse>, t: Throwable) {
            errorMessage.value = Resources.getSystem().getString(R.string.failed)
        }
    }

    private var tvSeriesGenreListCallback = object : Callback<TvSeriesGenreResponse> {
        override fun onResponse(
            call: Call<TvSeriesGenreResponse>,
            response: Response<TvSeriesGenreResponse>
        ) {
            if (response.isSuccessful) {
                tvSeriesGenreListResponse.value = response.body()
            }else{
                errorMessage.value = Resources.getSystem().getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<TvSeriesGenreResponse>, t: Throwable) {
            errorMessage.value = Resources.getSystem().getString(R.string.failed)
        }
    }

    fun getMovieList(language: String, page: Int) {
        TMDBApi.getMovies(page,language,populerFilmCallBack)
    }

    fun getGenreList(language: String) {
        TMDBApi.getGenreList(language,genreListCallback)
    }

    fun getVisionsFilmList(language: String){
        TMDBApi.getVisionsMovieList(language,visionsMovieListCallback)
    }

    fun getTopRatedTvList(page: Int,language: String){
        TMDBApi.getTopRatedTv(page, language,topRatedTvCallBack)
    }

    fun getTvSeriesList(language: String){
        TMDBApi.getPopularTvSeriesList(language,tvSeriesCallBack)
    }

    fun getTvSeriesGenreList(language: String) {
        TMDBApi.getTvSeriesGenreList(language,tvSeriesGenreListCallback)
    }

}
