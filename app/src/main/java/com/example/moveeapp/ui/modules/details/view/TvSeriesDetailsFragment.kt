package com.example.moveeapp.ui.modules.details.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.moveeapp.BuildConfig
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApiUserSingleton
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBRatedSingleton
import com.example.moveeapp.data.model.tmdb.Cast
import com.example.moveeapp.data.model.tmdb.PostRate
import com.example.moveeapp.data.model.tmdb.TvSeries
import com.example.moveeapp.databinding.FragmentTvSeriesDetailsBinding
import com.example.moveeapp.ui.base.GenreUtil
import com.example.moveeapp.ui.base.UIConstants
import com.example.moveeapp.ui.base.ViewModelFactory
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.GenericAdapter
import com.example.moveeapp.ui.components.movierecyclerview.util.OnItemClickListener
import com.example.moveeapp.ui.modules.details.base.DetailsBaseFragment
import com.example.moveeapp.ui.modules.details.viewModel.TvSeriesDetailsViewModel
import com.example.moveeapp.ui.modules.main.viewModel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_details.*
import java.util.*

class TvSeriesDetailsFragment : DetailsBaseFragment(), OnItemClickListener, RatingBar.OnRatingBarChangeListener {

    lateinit var myTvSeries: TvSeries
    lateinit var binding: FragmentTvSeriesDetailsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentTvSeriesDetailsBinding.inflate(inflater, container, false)

        val view = binding.root

        tvSeriesDetailsViewModel = ViewModelProvider(this, ViewModelFactory()).get(TvSeriesDetailsViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerLiveData()
        showProgress()
        setDataToUI()
        tvSeriesDetailsViewModel.getTvDetailsList(myTvSeries.id, Locale.getDefault().language)
        tvSeriesDetailsViewModel.getTvCreditList(myTvSeries.id)
        tvSeriesDetailsViewModel.getRateTvSeries(TMDBApiUserSingleton.accountId, Locale.getDefault().language)
        clickToTheRate()
        movie_rating_bar?.onRatingBarChangeListener = this
        clickShareButton()

    }
    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if(menuVisible){
            GenericAdapter.setListener(this)
        }
    }

    private fun registerLiveData() {
        tvSeriesDetailsViewModel.tvDetailsResponse.observe(viewLifecycleOwner, Observer {
            it?.let {tvDetails->
                val tvSeriesRunTime = view?.findViewById<TextView>(R.id.details_txt_time)
                if(tvDetails.episodeRunTime.isNotEmpty()) {
                    tvSeriesRunTime?.text = it.episodeRunTime.first().toString().plus(UIConstants.MIN)
                }
                val seasons = view?.findViewById<TextView>(R.id.details_seasons)
                seasons?.text = it.numberOfSeasons.toString().plus(UIConstants.SEASONS)
            }
        })

        tvSeriesDetailsViewModel.tvCreditResponse.observe(viewLifecycleOwner, Observer {tvCreditResponse ->
            tvCreditResponse?.let { tvCredit ->
                dismissProgress()
                 binding.tvSeriesNestedScroll.visibility = View.VISIBLE
                initializeCastRecyclerview(tvCredit.cast)
                val creators = view?.findViewById<TextView>(R.id.details_txt_creators_name)
                if(tvCredit.crew.isNotEmpty()){
                    creators?.text = tvCredit.crew.first().name
                }
            }
        })

        tvSeriesDetailsViewModel.rateTvSeriesResponse.observe(viewLifecycleOwner, Observer { ratedTvSeriesResponse ->
            ratedTvSeriesResponse?.let { ratedTvSeries->
                TMDBRatedSingleton.ratedTvSeries = ratedTvSeries.results
                TMDBRatedSingleton.ratedTvSeries.forEach{ myRatedMovie->
                    if(myTvSeries.id == myRatedMovie.id){
                        binding.detailsTxtRate.text = UIConstants.RATE  + myRatedMovie.rating.toString()

                    }

                }
            }

        })

        tvSeriesDetailsViewModel.postRatingTvSeriesResponse.observe(viewLifecycleOwner, Observer { postRateTvSeriesResponse->
            postRateTvSeriesResponse?.let {postRateTvSeries ->
                if(postRateTvSeries.success){
                    Snackbar.make(requireActivity().findViewById(android.R.id.content), UIConstants.RATE_HAS_SAVED, Snackbar.LENGTH_SHORT).show()
                }
            }

        })
    }


    private fun initializeCastRecyclerview(castList: List<Cast>) {
        val movieDetailsCastRecyclerViewAdapter = GenericAdapter(this, castList)
        binding.movieDetailsCastRecyclerview?.adapter = movieDetailsCastRecyclerViewAdapter


    }

    private fun setDataToUI(){
        arguments?.let{
            myTvSeries = TvSeriesDetailsFragmentArgs.fromBundle(it).myTvSeries
        }

        val textView = view?.findViewById<TextView>(R.id.tvSeries_details_title)
        val image = view?.findViewById<ImageView>(R.id.tv_series_details_img)
        val overview = view?.findViewById<TextView>(R.id.tvSeries_detail_overview)
        val vote = view?.findViewById<TextView>(R.id.tvSeries_details_txt_vote)
        val genre = view?.findViewById<TextView>(R.id.tvSeries_details_genre)
        val time = view?.findViewById<TextView>(R.id.details_txt_realise_time)

        textView?.text = myTvSeries.name

        image?.let { image ->
            Glide.with(TMDBApi.context).load(BuildConfig.IMAGE_URL + myTvSeries.posterPath).centerCrop().into(image)
        }
        overview?.text = myTvSeries.overview
        vote?.text = myTvSeries.voteAverage.toString()

        myTvSeries.let{ myTvSeries ->
            genre?.text = GenreUtil.getGenres(myTvSeries.genreIds, MainActivityViewModel.tvSeriesGenreList)
        }

        time?.text = myTvSeries.firstAirDate

    }
    private fun clickToTheRate(){
        val rateButton = view?.findViewById<ImageView>(R.id.detail_img_rate)
        val movieRatingBar = view?.findViewById<RatingBar>(R.id.movie_rating_bar)
        val layout = view?.findViewById<LinearLayout>(R.id.details_linear_img_share)
        var showingFirst = true

        rateButton?.setOnClickListener {
            if(showingFirst){
                movieRatingBar?.visibility = View.VISIBLE
                layout?.visibility = View.GONE
                showingFirst = false
            } else{
                movieRatingBar?.visibility = View.GONE
                layout?.visibility = View.VISIBLE
                showingFirst = true
            }

        }

    }

    private fun clickShareButton(){

        binding.detailsImgShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, UIConstants.SHARE_VALUE_TV + myTvSeries.id)
                type = UIConstants.SHARE_TYPE
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }

    }


    override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {

        val rateTvSeries = p1 * UIConstants.TWO

        tvSeriesDetailsViewModel.postRatingTvSeries(myTvSeries.id, PostRate(rateTvSeries))
        binding.detailsTxtRate.text =  UIConstants.RATE + "($rateTvSeries)"

    }

    override fun onItemClick(clickedItem: Any) {
        val myCast = clickedItem as Cast
        val action = TvSeriesDetailsFragmentDirections.actionTvSeriesDetailsFragmentToCastDetailsFragment(myCast)
        Navigation.findNavController(requireView()).navigate(action)
    }
}

