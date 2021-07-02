package com.example.moveeapp.ui.modules.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moveeapp.R
import com.example.moveeapp.application.MoveeApplication
import com.example.moveeapp.data.components.network.repository.tmdb.model.TopRatedTvResponse
import com.example.moveeapp.data.model.tmdb.TvSeries
import com.example.moveeapp.ui.base.UIConstants
import com.example.moveeapp.ui.base.ViewModelFactory
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.*
import com.example.moveeapp.ui.components.movierecyclerview.util.OnItemClickListener
import com.example.moveeapp.ui.components.movierecyclerview.util.RecyclerViewItemDecoration
import com.example.moveeapp.ui.model.CarauselTvSeriesItem
import com.example.moveeapp.ui.modules.main.base.MainBaseFragment
import com.example.moveeapp.ui.modules.main.viewModel.MainActivityViewModel
import java.util.*
import kotlin.collections.ArrayList

class TvSeriesFragment : MainBaseFragment(), OnItemClickListener {

    private var mainRecyclerView: RecyclerView? = null
    private var sectionList : List<BaseHeaderModel> = ArrayList()
    private lateinit var tItemList: List<BaseRecyclerviewItem>
    var recyclerViewList: List<RecyclerViewSection> = ArrayList()
    lateinit var mainRecyclerViewAdapter : GenericAdapter

    var page: Int = UIConstants.ONE
    var isScrolling = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tv_series_recyclerview, container, false)

        mainViewModel = ViewModelProvider(this, ViewModelFactory()).get(MainActivityViewModel::class.java)

        mainRecyclerView = view.findViewById(R.id.tvseries_recyclerview)

        setLists()
        showProgress()
        registerLiveData()
        mainViewModel.getTvSeriesGenreList(Locale.getDefault().language)
        return view
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if(menuVisible){
            GenericAdapter.setListener(this)
        }
    }


    private fun registerLiveData() {
        mainViewModel.tvSeriesGenreListResponse.observe(viewLifecycleOwner, Observer {
            it?.let {tvSeriesGenreResponse ->
                MainActivityViewModel.tvSeriesGenreList = tvSeriesGenreResponse.genres
                mainViewModel.getTopRatedTvList(page, Locale.getDefault().language)
            }
        })
        mainViewModel.topRatedTvResponse.observe(viewLifecycleOwner, Observer {
            it?.let { topRatedTv ->
                countPage(topRatedTv)
            }
        })
        mainViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let { errorMessage ->
                alertForError(errorMessage)
            }
        })

        mainViewModel.tvSeriesResponse.observe(viewLifecycleOwner, Observer {
            it?.let {popularTvSeries ->
                MainActivityViewModel.popularTvSeries = popularTvSeries.results
                mainViewModel.tvSeriesGenreListResponse.value?.let{genre ->
                    mainViewModel.topRatedTvResponse.value?.let{topRated->
                        populateList(topRated.results)
                        dismissProgress()
                        initRecyclerview()
                    }
                }
            }
        })
    }

    private fun countPage(topRatedTv: TopRatedTvResponse) {
        if (page == UIConstants.ONE) {
            page++
            mainViewModel.getTvSeriesList(Locale.getDefault().language)
            return
        }
        mainRecyclerViewAdapter.addData(topRatedTv.results)
        isScrolling = false

    }


    private fun initRecyclerview() {
        mainRecyclerViewAdapter = GenericAdapter(this,tItemList)
        mainRecyclerView?.adapter = mainRecyclerViewAdapter
        mainRecyclerView?.addItemDecoration(RecyclerViewItemDecoration(mainRecyclerViewAdapter))
        val layoutManager = GridLayoutManager(MoveeApplication.appContext, UIConstants.TWO)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == UIConstants.ZERO || position == UIConstants.ONE || position == UIConstants.TWO) {
                   UIConstants.TWO
                } else {
                    UIConstants.ONE
                }
            }
        }
        mainRecyclerView!!.layoutManager = layoutManager

        mainRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItem = layoutManager.itemCount
                val scroolOutItems = layoutManager.findLastVisibleItemPosition()
                val double =  scroolOutItems.toDouble()/totalItem.toDouble()
                val biggerThanZeroPointEight = (double >= UIConstants.ZERO_POINT_EIGHT)

                if (!isScrolling && biggerThanZeroPointEight) {
                    isScrolling = true
                    mainViewModel.getTopRatedTvList(page, Locale.getDefault().language)
                    page++
                }

            }
        })


    }
    private fun setLists() {
        sectionList = mainViewModel.getSectionList(UIConstants.TV_SERIES, UIConstants.TOP_RATED_SERIES,UIConstants.TWO)
        recyclerViewList = mainViewModel.getRecyclerList(this, UIConstants.ONE)
    }

    private fun populateList(itemList: List<BaseRecyclerviewItem>){
        tItemList = concatenate(itemList)
        (tItemList as ArrayList).add(sectionList[0].position, sectionList[0] as FirstHeader)
        (tItemList as ArrayList).add(recyclerViewList[0].position, recyclerViewList[0])
        (tItemList as ArrayList).add(sectionList[1].position, sectionList[1] as SecondHeader)
        print(tItemList)
    }

    private fun <T> concatenate(vararg lists: List<T>): List<T> {
        val result: MutableList<T> = ArrayList()
        for (list in lists) {
            result += list
        }
        return result
    }

    override fun onItemClick(clickedItem: Any) {
        val tvSeries : TvSeries = when (clickedItem){
            is CarauselTvSeriesItem -> {
                clickedItem.tvSeries
            } else -> {
                clickedItem as TvSeries
            }
        }
            val action = MainFragmentDirections.actionMainFragmentToTvSeriesDetailsFragment(tvSeries)
            Navigation.findNavController(requireView()).navigate(action)

    }
}

