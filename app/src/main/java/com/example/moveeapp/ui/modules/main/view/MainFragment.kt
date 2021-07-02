package com.example.moveeapp.ui.modules.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApiFavoriteMovieListSingleton
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApiUserSingleton
import com.example.moveeapp.ui.base.UIConstants
import com.example.moveeapp.ui.base.ViewModelFactory
import com.example.moveeapp.ui.components.movierecyclerview.ViewPagerAdapter
import com.example.moveeapp.ui.model.FavoriteMovieItem
import com.example.moveeapp.ui.modules.main.base.MainBaseFragment
import com.example.moveeapp.ui.modules.main.viewModel.MainFragmentViewModel
import com.google.android.material.tabs.TabLayout
import java.util.*


class MainFragment : MainBaseFragment() {

    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout

    val tabSelected = intArrayOf(
        R.drawable.ic_icons_tabbar_movie_selected,
        R.drawable.ic_icons_tv_series_tabbar_selected,
        R.drawable.ic_icons_search_tabbar_selected,
        R.drawable.ic_icons_profil_tabbar_selected
    )
    val tabsUnselected = intArrayOf(
        R.drawable.ic_icons_movie_tabbar_unselected,
        R.drawable.ic_icons_tv_series_tabbar_unselected,
        R.drawable.ic_icons_search_tabbar_unselected,
        R.drawable.ic_icons_profil_tabbar_unselected
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        mainFragmentViewModel = ViewModelProvider(this, ViewModelFactory()).get(MainFragmentViewModel::class.java)

        registerLiveData()

        if(TMDBApiUserSingleton.isGuest == false){
            mainFragmentViewModel.getAccountDetail()
        }


        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
        registerViewPagerWithTabs()
    }

    fun setUpUI(){

        viewPager = requireView().findViewById(R.id.viewPager)
        tabLayout = requireView().findViewById(R.id.tabLayout)

    }

    fun registerViewPagerWithTabs(){

        val adapterViewPager =
            ViewPagerAdapter(
                requireActivity().supportFragmentManager,
                lifecycle
            )
        viewPager.adapter = adapterViewPager


        viewPager.isUserInputEnabled = false

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected( position: Int) {
                tabLayout.getTabAt(position)?.select()

                val size = UIConstants.SIZE_LENGTH

                for (i in UIConstants.ZERO..size) {
                    if (i == position) {
                        tabLayout.getTabAt(i)?.setIcon(tabSelected[i])
                    } else {
                        tabLayout.getTabAt(i)?.setIcon(tabsUnselected[i])
                    }
                }
            }
        })

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position

            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun registerLiveData() {
        mainFragmentViewModel.accountDetailResponse.observe(viewLifecycleOwner, Observer { accountResponse ->
                accountResponse?.let { account ->
                    TMDBApiUserSingleton.accountId = account.id
                    TMDBApiUserSingleton.name = account.name
                    TMDBApiUserSingleton.userName = account.username
                        if(TMDBApiUserSingleton.isGuest == false){
                            mainFragmentViewModel.getFavoriteMovie(TMDBApiUserSingleton.accountId, Locale.getDefault().language)
                        }



                }
            })

        mainFragmentViewModel.favoriteMovieResponse.observe(viewLifecycleOwner, Observer {favoriteMovieResponse ->
            favoriteMovieResponse?.let{ favoriteMovie->
                val favoriteMovieList =  ArrayList<FavoriteMovieItem>()
                for(item in favoriteMovie.results){
                    val favoriteItem = FavoriteMovieItem(item)
                    favoriteMovieList.add(favoriteItem)
                }
                TMDBApiFavoriteMovieListSingleton.favoriteMovieList = favoriteMovieList
                mainFragmentViewModel.favoriteList.value = TMDBApiFavoriteMovieListSingleton.favoriteMovieList

            }
        })
    }

}