package com.example.moveeapp.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Ecem Suren on 15.10.2020.
 */
open class BaseViewActivity : AppCompatActivity() {

    //I will change  deprecated method which is progressDialog
    private val progressDialog by lazy { ProgressDialog(this) }
    lateinit var baseViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
        registerObserver()
    }

    private fun initialize() {
        baseViewModel = ViewModelProvider(this, ViewModelFactory()).get(BaseViewModel::class.java)
    }

    private fun registerObserver() {
        baseViewModel.loading.observe(this, Observer { loading ->
            if (loading) {
                showLoading()
            } else {
                dissmisLoading()
            }
        })
    }



    private fun showLoading() {
        progressDialog.show()
    }

    private fun dissmisLoading() {
        progressDialog.dismiss()
    }
}