package com.example.moveeapp.ui.modules.details.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.moveeapp.R
import com.example.moveeapp.application.MoveeApplication
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.data.components.network.repository.tmdb.model.PostRateResponse
import com.example.moveeapp.data.components.network.repository.tmdb.model.TvCreditResponse
import com.example.moveeapp.data.components.network.repository.tmdb.model.TvDetailsResponse
import com.example.moveeapp.data.components.network.repository.tmdb.model.TvSeriesResponse
import com.example.moveeapp.data.model.tmdb.PostRate
import com.example.moveeapp.ui.modules.details.base.DetailsBaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ecem Suren on 10.10.2020.
 */
class TvSeriesDetailsViewModel: DetailsBaseViewModel() {

    val tvDetailsResponse: MutableLiveData<TvDetailsResponse> = MutableLiveData()
    val tvCreditResponse: MutableLiveData<TvCreditResponse> = MutableLiveData()
    val rateTvSeriesResponse: MutableLiveData<TvSeriesResponse> = MutableLiveData()
    val postRatingTvSeriesResponse: MutableLiveData<PostRateResponse> = MutableLiveData()

    private var tvDetailsCallback = object : Callback<TvDetailsResponse> {
        override fun onResponse(
            call: Call<TvDetailsResponse>,
            response: Response<TvDetailsResponse>
        ) {
            if (response.isSuccessful) {
                tvDetailsResponse.value = response.body()
            }else{
                errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<TvDetailsResponse>, t: Throwable) {
            errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }

    private var tvCreditCallBack = object : Callback<TvCreditResponse> {
        override fun onResponse(
            call: Call<TvCreditResponse>,
            response: Response<TvCreditResponse>
        ) {
            if (response.isSuccessful) {
                tvCreditResponse.value = response.body()
            }else{
                errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<TvCreditResponse>, t: Throwable) {
            errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }
    private var rateTvSeriesCallBack = object : Callback<TvSeriesResponse> {
        override fun onResponse(
            call: Call<TvSeriesResponse>,
            response: Response<TvSeriesResponse>
        ) {
            if (response.isSuccessful) {
                rateTvSeriesResponse.value = response.body()
            }else{
                errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<TvSeriesResponse>, t: Throwable) {
            errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }

    private var postRatingTvSeriesCallback = object : Callback<PostRateResponse> {
        override fun onResponse(
            call: Call<PostRateResponse>,
            response: Response<PostRateResponse>
        ) {
            if (response.isSuccessful) {
                postRatingTvSeriesResponse.value = response.body()
            }else{
                errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<PostRateResponse>, t: Throwable) {
            errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }


    fun getTvDetailsList(tvSeriesId: Int, language: String) {
        TMDBApi.getTvSeriesDetails(language,tvSeriesId, tvDetailsCallback)
    }

    fun getTvCreditList(tvSeriesId: Int) {
        TMDBApi.getTvSeriesCredit(tvSeriesId, tvCreditCallBack)
    }

    fun getRateTvSeries(accountId: Int, language: String){
        TMDBApi.getRateTvSeries(language,accountId, rateTvSeriesCallBack)
    }

    fun postRatingTvSeries(tvId: Int, value: PostRate){
        TMDBApi.postRatingTvSeries(tvId, value, postRatingTvSeriesCallback)
    }

}