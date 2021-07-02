package com.example.moveeapp.ui.modules.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.moveeapp.R
import com.example.moveeapp.ui.base.BaseViewActivity
import com.example.moveeapp.ui.base.UIConstants
import com.example.moveeapp.ui.modules.main.view.MainActivityView

class SplashActivity : BaseViewActivity() {

    private val updateHandler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        updateHandler.postDelayed({navigateMain()}, UIConstants.SPLASH_TIME)
    }

    private fun navigateMain() {
        val intent = Intent(this, MainActivityView::class.java)
        startActivity(intent)
        finish()

    }
}