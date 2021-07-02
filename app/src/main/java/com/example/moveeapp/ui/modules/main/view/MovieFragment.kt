package com.example.moveeapp.ui.modules.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moveeapp.R
import com.example.moveeapp.application.MoveeApplication
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApiFavoriteMovieListSingleton
import com.example.moveeapp.data.components.network.repository.tmdb.model.MovieResponse
import com.example.moveeapp.data.model.tmdb.Movie
import com.example.moveeapp.ui.base.UIConstants
import com.example.moveeapp.ui.base.ViewModelFactory
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.*
import com.example.moveeapp.ui.components.movierecyclerview.util.OnItemClickListener
import com.example.moveeapp.ui.components.movierecyclerview.util.RecyclerViewItemDecoration
import com.example.moveeapp.ui.model.CarouselMovieItem
import com.example.moveeapp.ui.model.CastMovieItem
import com.example.moveeapp.ui.model.FavoriteMovieItem
import com.example.moveeapp.ui.modules.main.base.MainBaseFragment
import com.example.moveeapp.ui.modules.main.viewModel.MainActivityViewModel
import java.util.*
import kotlin.collections.ArrayList


class MovieFragment : MainBaseFragment(), OnItemClickListener {

    private var mainRecyclerView: RecyclerView? = null
    private lateinit var tItemList: List<BaseRecyclerviewItem>
    var recyclerViewList: List<RecyclerViewSection> = ArrayList()
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var mainRecyclerViewAdapter: GenericAdapter
    var page: Int = UIConstants.ONE
    var isScrolling = false
    private lateinit var action: NavDirections


    companion object {
        var sectionList: List<BaseHeaderModel> = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_movie, container, false)

        mainViewModel =
            ViewModelProvider(this, ViewModelFactory()).get(MainActivityViewModel::class.java)
        mainRecyclerView = view.findViewById(R.id.main_recyclerview)

        registerLiveData()
        setLists()
        showProgress()
        mainViewModel.getGenreList(Locale.getDefault().language)
        return view
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            GenericAdapter.setListener(this)
            mainViewModel.favoriteList.value = TMDBApiFavoriteMovieListSingleton.favoriteMovieList

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().supportFragmentManager.findFragmentById(R.id.main_container)
    }

    private fun setLists() {
        sectionList = mainViewModel.getSectionList(
            UIConstants.MOVIES_HEADER,
            UIConstants.POPULAR_MOVIES,
            UIConstants.ZERO
        )
        recyclerViewList = mainViewModel.getRecyclerList(this, UIConstants.ZERO)
    }


    private fun registerLiveData() {

        mainViewModel.genreListResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                MainActivityViewModel.genreList = it.genres
                mainViewModel.getMovieList(Locale.getDefault().language,page)
            }
        })

        mainViewModel.movieListResponse.observe(viewLifecycleOwner, Observer {
            it?.let { movieResponse ->
                countPage(movieResponse)
            }
        })

        mainViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let { errorMessage ->
                alertForError(errorMessage)
            }
        })
        mainViewModel.visionsFilmResponse.observe(viewLifecycleOwner, Observer {
            it?.let { visionResponse ->
                MainActivityViewModel.visionFilm = visionResponse.results
                mainViewModel.genreListResponse.value?.let { genre ->
                    mainViewModel.movieListResponse.value?.let { movie ->
                        populateList(movie.results)
                        dismissProgress()
                        initializeRecyclerview()
                        registerFavoriteList()
                    }
                }
            }
        })

    }

    private fun registerFavoriteList(){
        mainViewModel.favoriteList.observe(viewLifecycleOwner, Observer {
            mainRecyclerViewAdapter.notifyDataSetChanged()
            mainRecyclerView?.invalidate()
        })
    }

    private fun populateList(itemList: List<BaseRecyclerviewItem>) {
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

    fun initializeRecyclerview() {

        linearLayoutManager =
            LinearLayoutManager(MoveeApplication.appContext, LinearLayoutManager.VERTICAL, false)
        mainRecyclerView?.layoutManager = linearLayoutManager

        mainRecyclerViewAdapter = GenericAdapter(this, tItemList)

        mainRecyclerView?.addItemDecoration(RecyclerViewItemDecoration(mainRecyclerViewAdapter))

        mainRecyclerView?.adapter = mainRecyclerViewAdapter

        mainRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItem = linearLayoutManager.itemCount
                val lastItems = linearLayoutManager.findLastVisibleItemPosition()
                val double = lastItems.toDouble() / totalItem.toDouble()
                val biggerThanZeroPointEight = (double >= UIConstants.ZERO_POINT_EIGHT)

                if (!isScrolling && biggerThanZeroPointEight) {
                    isScrolling = true
                    mainViewModel.getMovieList(Locale.getDefault().language,page)
                    page++
                }
            }
        })


    }

    private fun countPage(movieResponse: MovieResponse) {
        if (page == UIConstants.ONE) {
            page++
            mainViewModel.getVisionsFilmList(Locale.getDefault().language)
        } else {
            mainRecyclerViewAdapter.addData(movieResponse.results)
            isScrolling = false
            dismissProgress()
        }
    }

    override fun onItemClick(clickedItem: Any) {

        if (clickedItem is FirstHeader) {
            action = MainFragmentDirections.actionMainFragmentToMapsFragment()
        } else {
            val myMovie: Movie = when (clickedItem) {
                is CastMovieItem -> {
                    clickedItem.movie
                }
                is CarouselMovieItem -> {
                    clickedItem.movie
                }
                else -> {
                    tItemList.filterIsInstance<Movie>().forEach { movie ->
                        TMDBApiFavoriteMovieListSingleton.favoriteMovieList.forEach { favoriteItem ->
                            if (FavoriteMovieItem(movie) == favoriteItem) {
                                movie.isFavorite = true
                            }
                        }
                    }
                    clickedItem as Movie
                }
            }
            action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(myMovie)
        }

        Navigation.findNavController(requireView()).navigate(action)
    }
}


