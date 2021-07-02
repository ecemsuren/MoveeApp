package com.example.moveeapp.ui.modules.main.viewModel

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.data.components.network.repository.tmdb.model.SearchResponse
import com.example.moveeapp.ui.modules.main.base.MainBaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ecem Suren on 20.10.2020.
 */
class SearchFragmentViewModel: MainBaseViewModel() {

    val searchResponse : MutableLiveData<SearchResponse> = MutableLiveData()

    private var searchListCallback = object : Callback<SearchResponse> {
        override fun onResponse(
            call: Call<SearchResponse>,
            response: Response<SearchResponse>
        ) {
            if (response.isSuccessful) {
                searchResponse.value = response.body()
            }else{
                errorMessage.value = Resources.getSystem().getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
            errorMessage.value = Resources.getSystem().getString(R.string.failed)
        }
    }


    fun getSearchList(query: String, language: String) {
        TMDBApi.getSearch(language,searchListCallback, query)
    }

}