package com.example.moveeapp.ui.modules.main.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.moveeapp.R
import com.example.moveeapp.application.MoveeApplication
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.data.components.network.repository.tmdb.model.AccountResponse
import com.example.moveeapp.data.components.network.repository.tmdb.model.MovieResponse
import com.example.moveeapp.ui.modules.main.base.MainBaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ecem Suren on 3.11.2020.
 */
class MainFragmentViewModel: MainBaseViewModel() {

    val accountDetailResponse: MutableLiveData<AccountResponse> = MutableLiveData()
    val favoriteMovieResponse: MutableLiveData<MovieResponse> = MutableLiveData()

    private var accountDetailCallback = object : Callback<AccountResponse> {
        override fun onResponse(
            call: Call<AccountResponse>,
            response: Response<AccountResponse>
        ) {
            if (response.isSuccessful) {
                accountDetailResponse.value = response.body()
            }else{
                errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<AccountResponse>, t: Throwable) {
              errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }

    private var favoriteMovieCallback = object : Callback<MovieResponse> {
        override fun onResponse(
            call: Call<MovieResponse>,
            response: Response<MovieResponse>
        ) {
            if (response.isSuccessful) {
                favoriteMovieResponse.value = response.body()
            }else{
                  errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
              errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }

    fun getAccountDetail() {
        TMDBApi.getUserAccountDetails(accountDetailCallback)
    }

    fun getFavoriteMovie(accountId: Int, language: String) {
        TMDBApi.getFavoriteMovie(language,accountId, favoriteMovieCallback)
    }

}