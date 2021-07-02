package com.example.moveeapp.ui.components.movierecyclerview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moveeapp.ui.modules.main.view.MovieFragment
import com.example.moveeapp.ui.modules.main.view.ProfileFragment
import com.example.moveeapp.ui.modules.main.view.SearchFragment
import com.example.moveeapp.ui.modules.main.view.TvSeriesFragment

/**
 * Created by Ecem Suren on 6.10.2020.
 */

class ViewPagerAdapter(manager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(manager, lifecycle) {

    var fragmentList: ArrayList<Fragment> = arrayListOf(MovieFragment(), TvSeriesFragment(), SearchFragment(),ProfileFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}
