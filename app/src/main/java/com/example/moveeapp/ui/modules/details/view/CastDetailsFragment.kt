package com.example.moveeapp.ui.modules.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.moveeapp.BuildConfig
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.data.components.network.repository.tmdb.model.PeopleDetailsResponse
import com.example.moveeapp.data.model.tmdb.Cast
import com.example.moveeapp.data.model.tmdb.Movie
import com.example.moveeapp.databinding.CastDetailsBinding
import com.example.moveeapp.ui.base.UIConstants
import com.example.moveeapp.ui.base.ViewModelFactory
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.GenericAdapter
import com.example.moveeapp.ui.components.movierecyclerview.util.OnItemClickListener
import com.example.moveeapp.ui.model.CarouselMovieItem
import com.example.moveeapp.ui.model.CastMovieItem
import com.example.moveeapp.ui.modules.details.base.DetailsBaseFragment
import com.example.moveeapp.ui.modules.details.viewModel.CastDetailsViewModel
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Ecem Suren on 12.10.2020.
 */

class CastDetailsFragment : DetailsBaseFragment(), OnItemClickListener {

    lateinit var myCast: Cast
    var isCheck = true
    lateinit var binding: CastDetailsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CastDetailsBinding.inflate(inflater, container, false)

        val view = binding.root

        castDetailsViewModel =  ViewModelProvider(this, ViewModelFactory()).get(CastDetailsViewModel::class.java)

        setTextViewMoreToUI()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerLiveData()
        setDataUI()

        myCast.id?.let { castDetailsViewModel.getPeopleDetailsList(it, Locale.getDefault().language) }
        myCast.id?.let { castDetailsViewModel.getMoviesOfPeopleList(it, Locale.getDefault().language) }
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if(menuVisible){
            GenericAdapter.setListener(this)
        }
    }
    private fun registerLiveData(){

        castDetailsViewModel.peopleDetailsResponse.observe(viewLifecycleOwner, Observer { peopleDetailsResponse ->
            peopleDetailsResponse?.let { peopleDetails ->
                initialize(peopleDetails)
            }
        })

        castDetailsViewModel.moviesOfPeopleResponse.observe(viewLifecycleOwner, Observer { moviesOfPeopleResponse->
            moviesOfPeopleResponse?.let { moviesOfPeople->
                val castMovieList = ArrayList<CastMovieItem>()
                for (item in moviesOfPeople.cast) {
                    val castItem = CastMovieItem(item)
                    castMovieList.add(castItem)
                }
                initializeCastRecyclerview(castMovieList)
            }
        })
    }

    private fun initialize(peopleDetailResponse: PeopleDetailsResponse){
        if(peopleDetailResponse.biography.isNullOrBlank() ) {
            binding.castDetailsViewMore.visibility = View.GONE
        } else {
            binding.castDetailsViewMore.visibility = View.VISIBLE
            binding.castDetailsTxtOverview.text = peopleDetailResponse.biography
        }
        if(peopleDetailResponse.birthday == null) {
            binding.castTxtBorn.visibility = View.GONE
        } else {
            binding.castBornDate.text = peopleDetailResponse.birthday
        }
    }

    private fun initializeCastRecyclerview(castList: List<CastMovieItem>) {
        val moviesOfPeopleRecyclerViewAdapter = GenericAdapter(this, castList )
        binding.movieDetailsCastRecyclerview.adapter = moviesOfPeopleRecyclerViewAdapter
    }

    private fun setDataUI (){

        arguments?.let{
            myCast = CastDetailsFragmentArgs.fromBundle(it).myCast
        }


        binding.castDetailsName?.let{castName ->
            castName.text = myCast.name
        }
        binding.castDetailsImg?.let { image ->
            Glide.with(TMDBApi.context).load(BuildConfig.IMAGE_URL + myCast.profilePath).centerCrop()
                .into(image)
        }
    }

    private fun setTextViewMoreToUI(){
        binding.castDetailsViewMore.setOnClickListener {
            if(isCheck){
                binding.castDetailsTxtOverview.maxLines = UIConstants.MAX_LINE
                binding.castDetailsViewMore.text = UIConstants.BIOGRAPHY_SEE_LESS
                isCheck = false
            }else{
                binding.castDetailsTxtOverview.maxLines = UIConstants.MIN_LINE
                binding.castDetailsViewMore.text = UIConstants.BIOGRAPHY_SEE_MORE
                isCheck = true
            }
        }
    }

    override fun onItemClick(clickedItem: Any) {
        val myMovie: Movie = when (clickedItem) {
            is CastMovieItem -> {
                clickedItem.movie
            }
            is CarouselMovieItem -> {
                clickedItem.movie
            }
            else -> {
                clickedItem as Movie
            }
        }
        val action = CastDetailsFragmentDirections.actionCastDetailsFragmentToMovieDetailsFragment(myMovie)
        Navigation.findNavController(requireView()).navigate(action)

    }
}