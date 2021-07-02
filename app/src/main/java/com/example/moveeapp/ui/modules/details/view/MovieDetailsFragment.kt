package com.example.moveeapp.ui.modules.details.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.moveeapp.BuildConfig
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApiFavoriteMovieListSingleton
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApiUserSingleton
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBRatedSingleton
import com.example.moveeapp.data.model.tmdb.Cast
import com.example.moveeapp.data.model.tmdb.FavoriteMovie
import com.example.moveeapp.data.model.tmdb.Movie
import com.example.moveeapp.data.model.tmdb.PostRate
import com.example.moveeapp.databinding.FragmentMovieDetailsBinding
import com.example.moveeapp.ui.base.GenreUtil
import com.example.moveeapp.ui.base.UIConstants
import com.example.moveeapp.ui.base.ViewModelFactory
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.GenericAdapter
import com.example.moveeapp.ui.components.movierecyclerview.util.OnItemClickListener
import com.example.moveeapp.ui.model.FavoriteMovieItem
import com.example.moveeapp.ui.modules.details.base.DetailsBaseFragment
import com.example.moveeapp.ui.modules.details.viewModel.MovieDetailsViewModel
import com.example.moveeapp.ui.modules.main.viewModel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_details.*
import java.util.*


class MovieDetailsFragment : DetailsBaseFragment(), OnItemClickListener, RatingBar.OnRatingBarChangeListener{

    lateinit var movie: Movie
    lateinit var binding: FragmentMovieDetailsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        val view = binding.root

        movieDetailsViewModel =
            ViewModelProvider(this, ViewModelFactory()).get(MovieDetailsViewModel::class.java)

        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerLiveData()
        registerLikeLiveData()
        showProgress()
        setDataToUI()
        movieDetailsViewModel.getMovieCredit(movie.id)
        movieDetailsViewModel.getMovieDetailsList(movie.id, Locale.getDefault().language)
        movieDetailsViewModel.getRateMovie(TMDBApiUserSingleton.accountId, Locale.getDefault().language)

        clickToTheLikeButton()
        clickShareButton()
        movie_rating_bar?.onRatingBarChangeListener = this
        clickToTheRate()

        movie


    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            GenericAdapter.setListener(this)
        }
    }

    private fun registerLiveData() {
        movieDetailsViewModel.movieDetailsResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.detailsTxtTime.text = it.runtime.toString().plus(UIConstants.MIN)
            }
        })
        movieDetailsViewModel.movieCreditResponse.observe(viewLifecycleOwner, Observer { movieCreditResponse ->
            movieCreditResponse?.let { movieCredit ->
                dismissProgress()
                binding.movieNestedScroll.visibility = View.VISIBLE
                initializeCastRecyclerview(movieCredit.cast)
                binding.detailsTxtDirectorName.let {
                    if (!movieCredit.crew.isNullOrEmpty()) {
                        binding.detailsTxtDirectorName.text =
                            movieCredit.crew.first { it.job == UIConstants.DIRECTOR }.name
                    }
                }

                if (movieCredit.crew.any { it.job == UIConstants.EDITOR }) {
                    binding.detailsTxtWriterName.text = movieCredit.crew.first { it.job == UIConstants.EDITOR }.name
                }
                binding.detailsTxtStarName?.let {
                    it.text = movieCredit.cast.first().name
                }

            }
        })

        movieDetailsViewModel.rateMovieResponse.observe(viewLifecycleOwner, Observer { ratedMovieResponse ->
            ratedMovieResponse?.let { ratedMovie->
                TMDBRatedSingleton.ratedMovie = ratedMovie.results
                TMDBRatedSingleton.ratedMovie.forEach{ myRatedMovie->
                    if(movie.id == myRatedMovie.id){
                        binding.movieRatingBar.rating = myRatedMovie.rating.toFloat()
                        binding.detailsTxtRate.text = getString(R.string.details_rate)  + (myRatedMovie.rating.toString())
                    }

                }
            }

        })

        movieDetailsViewModel.postRatingMovieResponse.observe(viewLifecycleOwner, Observer { postRateMovieResponse->
            postRateMovieResponse?.let {postRateMovie ->
                if(postRateMovie.success){
                    val snackbar = Snackbar.make(requireActivity().findViewById(android.R.id.content), UIConstants.RATE_HAS_SAVED, Snackbar.LENGTH_SHORT)
                    snackbar.view.setBackgroundColor(requireActivity().getColor(R.color.vibrant_blue))
                    snackbar.show()
                }

            }

        })
    }

    private fun initializeCastRecyclerview(castList: List<Cast>) {
        val genericAdapter = GenericAdapter(this, castList)
        binding.movieDetailsCastRecyclerview?.adapter = genericAdapter
    }

    private fun setDataToUI() {
        arguments?.let {
            movie = MovieDetailsFragmentArgs.fromBundle(it).myMovie
        }

        if(TMDBApiUserSingleton.isGuest == true){
            binding.movieLikeHeart .visibility = View.GONE
            binding.likeLinearLayout.visibility = View.GONE
        }
        if (movie.isFavorite) {
            binding.movieLikeHeart.setImageResource(R.drawable.ic_icon_like_heart)
        } else {
            binding.movieLikeHeart.setImageResource(R.drawable.ic_icon_unliked_heart)
        }

        binding.tvSeriesDetailsTitle?.let {
            it.text = movie.title
        }

        binding.tvSeriesDetailsImg?.let { image ->
            Glide.with(TMDBApi.context).load(BuildConfig.IMAGE_URL + movie.posterPath).centerCrop()
                .into(image)
        }
        binding.tvSeriesDetailOverview?.let {
            it.text = movie.overview
        }

        binding.tvSeriesDetailsTxtVote?.text = movie.voteAverage.toString()

        movie.let { movie ->
            binding.tvSeriesDetailsGenre?.text =
                GenreUtil.getGenres(movie.genreIds, MainActivityViewModel.genreList)

            binding.detailsTxtRealiseTime?.text = movie.releaseDate
        }

    }

    override fun onItemClick(clickedItem: Any) {
        if (clickedItem is Cast) {
            val myCast = clickedItem
            val action =
                MovieDetailsFragmentDirections.actionMovieDetailsFragmentToCastDetailsFragment(
                    myCast
                )
            Navigation.findNavController(requireView()).navigate(action)
        }
    }

    private fun registerLikeLiveData() {
        movieDetailsViewModel.markFavoriteResponse.observe(
            viewLifecycleOwner,
            Observer { markFavoriteResponse ->
                markFavoriteResponse?.let { markFavorite ->
                    if (markFavorite.success) {

                        (TMDBApiFavoriteMovieListSingleton.favoriteMovieList as ArrayList).add(
                            FavoriteMovieItem(movie)
                        )

                    }

                }
            })
    }

    private fun clickToTheLikeButton() {
        binding.movieLikeHeart.setOnClickListener {

            movieDetailsViewModel.markFavorite(
                TMDBApiUserSingleton.accountId,
                FavoriteMovie(
                    UIConstants.MEDIA_TYPE_MOVIE,
                    movie.id,
                    !movie.isFavorite
                )
            )

            if (movie.isFavorite) {
                movie.isFavorite = false
                binding.movieLikeHeart.setImageResource(R.drawable.ic_icon_unliked_heart)

            } else {
                movie.isFavorite = true
                binding.movieLikeHeart.setImageResource(R.drawable.ic_icon_like_heart)
            }

        }
    }

    private fun clickShareButton(){

        binding.detailsImgShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, UIConstants.SHARE_VALUE + movie.id)
                type = UIConstants.SHARE_TYPE
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }

    }

    private fun clickToTheRate(){
        var showingFirst = true

        binding.detailImgRate.setOnClickListener {
            if(showingFirst){
                binding.movieRatingBar.visibility = View.VISIBLE
                binding.detailsLinearImgShare.visibility = View.GONE
                showingFirst = false
            } else{
                binding.movieRatingBar.visibility = View.GONE
                binding.detailsLinearImgShare.visibility = View.VISIBLE
                showingFirst = true
            }

        }

    }

    override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
            movieDetailsViewModel.postRatingMovie(movie.id, PostRate(p1))
            binding.detailsTxtRate.text = getString(R.string.details_rate) + "(${p1.toInt()})"

    }

}