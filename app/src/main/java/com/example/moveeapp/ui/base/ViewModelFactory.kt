package com.example.moveeapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moveeapp.ui.modules.details.base.DetailsBaseViewModel
import com.example.moveeapp.ui.modules.details.viewModel.CastDetailsViewModel
import com.example.moveeapp.ui.modules.details.viewModel.DetailsFragmentViewModel
import com.example.moveeapp.ui.modules.details.viewModel.MovieDetailsViewModel
import com.example.moveeapp.ui.modules.details.viewModel.TvSeriesDetailsViewModel
import com.example.moveeapp.ui.modules.login.viewmodel.LoginViewModel
import com.example.moveeapp.ui.modules.main.viewModel.MainActivityViewModel
import com.example.moveeapp.ui.modules.main.viewModel.MainFragmentViewModel
import com.example.moveeapp.ui.modules.main.viewModel.ProfileViewModel
import com.example.moveeapp.ui.modules.main.viewModel.SearchFragmentViewModel
import com.example.moveeapp.ui.modules.map.viewmodel.MapViewModel

/**
 * Created by Ecem Suren on 5.10.2020.
 */
class ViewModelFactory (): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == MainActivityViewModel().javaClass) {
            MainActivityViewModel() as T
        } else if (modelClass == DetailsFragmentViewModel().javaClass) {
            DetailsFragmentViewModel() as T
        } else if(modelClass == MovieDetailsViewModel().javaClass){
            MovieDetailsViewModel() as T
        } else if(modelClass == TvSeriesDetailsViewModel().javaClass){
            TvSeriesDetailsViewModel() as T
        } else if(modelClass == CastDetailsViewModel().javaClass) {
            CastDetailsViewModel() as T
        } else if (modelClass == SearchFragmentViewModel().javaClass) {
            SearchFragmentViewModel() as T
        }else if(modelClass == LoginViewModel().javaClass){
                LoginViewModel() as T
        } else if ( modelClass == ProfileViewModel().javaClass){
            ProfileViewModel() as T
        }  else if(modelClass == MapViewModel().javaClass){
            MapViewModel() as T
        } else if(modelClass == DetailsBaseViewModel().javaClass){
            DetailsBaseViewModel() as T
        } else if(modelClass == MainFragmentViewModel().javaClass){
            MainFragmentViewModel() as T
        } else {
            BaseViewModel() as T
        }

    }
}

