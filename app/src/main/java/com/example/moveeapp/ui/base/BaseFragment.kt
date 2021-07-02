package com.example.moveeapp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.moveeapp.ui.modules.main.view.MainActivityView

/**
 * Created by Ecem Suren on 5.10.2020.
 */

open class BaseFragment : Fragment(){

    lateinit var viewModel: BaseViewModel

    fun showProgress() {
        (activity as MainActivityView).baseViewModel.loading.value = true
    }

    fun dismissProgress() {
        (activity as MainActivityView).baseViewModel.loading.value = false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun alertForError(errorMessage: String) {

        AlertDialog.Builder(requireActivity()).setMessage(errorMessage).setCancelable(true).show()
    }

}