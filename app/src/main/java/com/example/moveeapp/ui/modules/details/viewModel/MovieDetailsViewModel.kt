package com.example.moveeapp.ui.modules.details.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.moveeapp.R
import com.example.moveeapp.application.MoveeApplication
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.data.components.network.repository.tmdb.model.*
import com.example.moveeapp.data.model.tmdb.FavoriteMovie
import com.example.moveeapp.data.model.tmdb.PostRate
import com.example.moveeapp.ui.modules.details.base.DetailsBaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ecem Suren on 10.10.2020.
 */
class MovieDetailsViewModel: DetailsBaseViewModel() {

    val movieDetailsResponse: MutableLiveData<MovieDetailsResponse> = MutableLiveData()
    val movieCreditResponse: MutableLiveData<MovieCreditResponse> = MutableLiveData()
    val rateMovieResponse: MutableLiveData<MovieResponse> = MutableLiveData()
    val postRatingMovieResponse: MutableLiveData<PostRateResponse> = MutableLiveData()
    val markFavoriteResponse: MutableLiveData<MarkFavoriteResponse> = MutableLiveData()


    private var movieDetailsCallback = object : Callback<MovieDetailsResponse> {
        override fun onResponse(
            call: Call<MovieDetailsResponse>,
            response: Response<MovieDetailsResponse>
        ) {
            if (response.isSuccessful) {
                movieDetailsResponse.value = response.body()
            }else{
                errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }


        override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
            errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }
    private var movieCreditCallback = object : Callback<MovieCreditResponse> {
        override fun onResponse(
            call: Call<MovieCreditResponse>,
            response: Response<MovieCreditResponse>
        ) {
            if (response.isSuccessful) {
                movieCreditResponse.value = response.body()
            }else{
                errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<MovieCreditResponse>, t: Throwable) {
            errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }

    private var rateMovieCallback = object : Callback<MovieResponse> {
        override fun onResponse(
            call: Call<MovieResponse>,
            response: Response<MovieResponse>
        ) {
            if (response.isSuccessful) {
                rateMovieResponse.value = response.body()
            }else{
                  errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
              errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }

    private var postRatingMovieCallback = object : Callback<PostRateResponse> {
        override fun onResponse(
            call: Call<PostRateResponse>,
            response: Response<PostRateResponse>
        ) {
            if (response.isSuccessful) {
                postRatingMovieResponse.value = response.body()
            }else{
                errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<PostRateResponse>, t: Throwable) {
            errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }

    private var markFavoriteCallback = object : Callback<MarkFavoriteResponse> {
        override fun onResponse(
            call: Call<MarkFavoriteResponse>,
            response: Response<MarkFavoriteResponse>
        ) {
            if (response.isSuccessful) {
                markFavoriteResponse.value = response.body()
            }else{
                errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<MarkFavoriteResponse>, t: Throwable) {
            errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }

    fun markFavorite(accountId: Int, markMovie: FavoriteMovie) {
        TMDBApi.postMarkFavoriteMovie(accountId, markMovie, markFavoriteCallback)
    }

    fun getMovieDetailsList(movieId: Int, language: String) {
        TMDBApi.getMovieDetails(language,movieId, movieDetailsCallback)
    }

    fun getMovieCredit(movieId: Int) {
        TMDBApi.getMovieCredit(movieId, movieCreditCallback)
    }

    fun getRateMovie(accountId: Int, language: String){
        TMDBApi.getRateMovie(language,accountId, rateMovieCallback)
    }

    fun postRatingMovie(movieId: Int, value: PostRate){
        TMDBApi.postRatingMovie(movieId, value, postRatingMovieCallback)
    }
}