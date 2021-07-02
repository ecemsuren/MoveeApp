package com.example.moveeapp.ui.modules.main.base

import com.example.moveeapp.ui.base.BaseFragment
import com.example.moveeapp.ui.modules.main.viewModel.MainActivityViewModel
import com.example.moveeapp.ui.modules.main.viewModel.MainFragmentViewModel
import com.example.moveeapp.ui.modules.main.viewModel.ProfileViewModel
import com.example.moveeapp.ui.modules.main.viewModel.SearchFragmentViewModel

/**
 * Created by Ecem Suren on 28.09.2020.
 */
open class MainBaseFragment : BaseFragment() {

    lateinit var mainViewModel : MainActivityViewModel
    lateinit var searchViewModel : SearchFragmentViewModel
    lateinit var profileViewModel: ProfileViewModel
    lateinit var mainFragmentViewModel: MainFragmentViewModel

}