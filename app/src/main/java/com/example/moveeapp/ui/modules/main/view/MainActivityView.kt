package com.example.moveeapp.ui.modules.main.view

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.moveeapp.R
import com.example.moveeapp.ui.modules.main.base.MainBaseViewActivity


class MainActivityView : MainBaseViewActivity() {

    private lateinit var navigationController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationController = Navigation.findNavController(this, R.id.main_container)

    }

}











