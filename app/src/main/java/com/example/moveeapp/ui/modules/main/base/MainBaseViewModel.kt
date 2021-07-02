package com.example.moveeapp.ui.modules.main.base

import androidx.lifecycle.MutableLiveData
import com.example.moveeapp.ui.base.BaseViewModel

/**
 * Created by Ecem Suren on 5.10.2020.
 */
open class MainBaseViewModel : BaseViewModel(){

    val errorMessage : MutableLiveData<String> = MutableLiveData()

}