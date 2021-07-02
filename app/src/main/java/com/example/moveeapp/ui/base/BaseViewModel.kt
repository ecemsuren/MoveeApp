package com.example.moveeapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.IBaseRecyclerviewItem

/**
 * Created by Ecem Suren on 5.10.2020.
 */

open class BaseViewModel : ViewModel() {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val favoriteList: MutableLiveData<List<IBaseRecyclerviewItem>> = MutableLiveData()



}