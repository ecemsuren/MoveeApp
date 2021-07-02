package com.example.moveeapp.ui.modules.details.base

import com.example.moveeapp.ui.base.BaseFragment
import com.example.moveeapp.ui.modules.details.viewModel.CastDetailsViewModel
import com.example.moveeapp.ui.modules.details.viewModel.MovieDetailsViewModel
import com.example.moveeapp.ui.modules.details.viewModel.TvSeriesDetailsViewModel

/**
 * Created by Ecem Suren on 5.10.2020.
 */
open class DetailsBaseFragment: BaseFragment() {

    lateinit var movieDetailsViewModel: MovieDetailsViewModel
    lateinit var tvSeriesDetailsViewModel: TvSeriesDetailsViewModel
    lateinit var castDetailsViewModel : CastDetailsViewModel
    lateinit var detailsBaseViewModel: DetailsBaseViewModel

}