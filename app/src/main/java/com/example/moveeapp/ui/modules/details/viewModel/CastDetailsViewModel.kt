package com.example.moveeapp.ui.modules.details.viewModel

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.data.components.network.repository.tmdb.model.MoviesOfPeopleResponse
import com.example.moveeapp.data.components.network.repository.tmdb.model.PeopleDetailsResponse
import com.example.moveeapp.ui.modules.details.base.DetailsBaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ecem Suren on 12.10.2020.
 */
class CastDetailsViewModel: DetailsBaseViewModel() {

    val peopleDetailsResponse: MutableLiveData<PeopleDetailsResponse> = MutableLiveData()
    val moviesOfPeopleResponse: MutableLiveData<MoviesOfPeopleResponse> = MutableLiveData()

    private var tvDetailsCallback = object : Callback<PeopleDetailsResponse> {
        override fun onResponse(
            call: Call<PeopleDetailsResponse>,
            response: Response<PeopleDetailsResponse>
        ) {
            if (response.isSuccessful) {
                peopleDetailsResponse.value = response.body()
            }else{
                errorMessage.value = Resources.getSystem().getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<PeopleDetailsResponse>, t: Throwable) {
            errorMessage.value = Resources.getSystem().getString(R.string.failed)
        }
    }

    private var moviesOfPeopleCallback = object : Callback<MoviesOfPeopleResponse> {
        override fun onResponse(
            call: Call<MoviesOfPeopleResponse>,
            response: Response<MoviesOfPeopleResponse>
        ) {
            if (response.isSuccessful) {
                moviesOfPeopleResponse.value = response.body()
            }else{
                errorMessage.value = Resources.getSystem().getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<MoviesOfPeopleResponse>, t: Throwable) {
            errorMessage.value = Resources.getSystem().getString(R.string.failed)
        }
    }

    fun getPeopleDetailsList(tvSeriesId: Int, language: String) {
        TMDBApi.getPeopleDetails(language,tvSeriesId, tvDetailsCallback)
    }

    fun getMoviesOfPeopleList(peopleId: Int, language: String){
        TMDBApi.getMoviesOfPeople(language,peopleId, moviesOfPeopleCallback)
    }

}