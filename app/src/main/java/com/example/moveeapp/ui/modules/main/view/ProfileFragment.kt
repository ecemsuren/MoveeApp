package com.example.moveeapp.ui.modules.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.moveeapp.R
import com.example.moveeapp.application.MoveeApplication
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApiFavoriteMovieListSingleton
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApiUserSingleton
import com.example.moveeapp.data.model.tmdb.Movie
import com.example.moveeapp.ui.base.ViewModelFactory
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.GenericAdapter
import com.example.moveeapp.ui.components.movierecyclerview.util.OnItemClickListener
import com.example.moveeapp.ui.model.FavoriteMovieItem
import com.example.moveeapp.ui.modules.main.base.MainBaseFragment
import com.example.moveeapp.ui.modules.main.viewModel.ProfileViewModel


class ProfileFragment : MainBaseFragment(), OnItemClickListener {

    lateinit var profileName: TextView
    private var favoriteReyclerview: RecyclerView? = null
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var genericAdapter: GenericAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profileViewModel =
            ViewModelProvider(this, ViewModelFactory()).get(ProfileViewModel::class.java)

        favoriteReyclerview = view.findViewById(R.id.profile_favorite_recyclerview)
        profileName = view.findViewById(R.id.profile_name)

        setProfileInformation()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickSettingAnimation()

    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            GenericAdapter.setListener(this)
            initRecyclerview()
        }
    }


    private fun setProfileInformation() {

        if (!TMDBApiUserSingleton.name.isNullOrEmpty()) {
            profileName.text = TMDBApiUserSingleton.name
        } else {
            profileName.text = TMDBApiUserSingleton.userName
        }
    }

    private fun initRecyclerview() {

        linearLayoutManager = LinearLayoutManager(MoveeApplication.appContext)
        favoriteReyclerview?.layoutManager = linearLayoutManager
        genericAdapter = GenericAdapter(this, TMDBApiFavoriteMovieListSingleton.favoriteMovieList)
        favoriteReyclerview?.adapter = genericAdapter

    }

    fun clickSettingAnimation(){
        val settingAnimation = view?.findViewById<LottieAnimationView>(R.id.animation_settings)
        settingAnimation?.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToAboutFragment()
            Navigation.findNavController(requireView()).navigate(action)

        }
    }

    override fun onItemClick(clickedItem: Any) {
         val myMovie: Movie = when(clickedItem) {
             is FavoriteMovieItem -> {
                 clickedItem.favoriteMovie
             }
             else -> {
                 clickedItem as Movie
             }

         }
        val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(myMovie)
        Navigation.findNavController(requireView()).navigate(action)

    }
}