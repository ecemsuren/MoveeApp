package com.example.moveeapp.ui.modules.main.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moveeapp.R
import com.example.moveeapp.application.MoveeApplication
import com.example.moveeapp.data.model.tmdb.Cast
import com.example.moveeapp.data.model.tmdb.Movie
import com.example.moveeapp.data.model.tmdb.Search
import com.example.moveeapp.data.model.tmdb.TvSeries
import com.example.moveeapp.ui.base.UIConstants
import com.example.moveeapp.ui.base.ViewModelFactory
import com.example.moveeapp.ui.components.movierecyclerview.MediaType
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseRecyclerviewItem
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.GenericAdapter
import com.example.moveeapp.ui.components.movierecyclerview.util.OnItemClickListener
import com.example.moveeapp.ui.modules.main.base.MainBaseFragment
import com.example.moveeapp.ui.modules.main.viewModel.SearchFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class SearchFragment : MainBaseFragment(), CoroutineScope, OnItemClickListener {

    private var searchReyclerview: RecyclerView? = null
    lateinit var genericAdapter: GenericAdapter
    lateinit var searchEditText: EditText
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var textWatcher: TextWatcher
    lateinit var cancel: TextView
    lateinit var noImage: ImageView
    private val totalList: ArrayList<BaseRecyclerviewItem> by lazy { ArrayList<BaseRecyclerviewItem>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        searchViewModel =
            ViewModelProvider(this, ViewModelFactory()).get(SearchFragmentViewModel::class.java)
        searchReyclerview = view.findViewById(R.id.search_recyclerview)
        searchEditText = view.findViewById(R.id.search_edit_text)
        cancel = view.findViewById(R.id.search_cancel)
        noImage = view.findViewById(R.id.search_img_no_result)

        noImage.visibility = View.GONE

        registerLiveData()
        requestToTheService()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchEditText.addTextChangedListener(textWatcher)

        clearSearchTextAndData(searchEditText)
    }
    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if(menuVisible){
            GenericAdapter.setListener(this)
        }
    }

    private fun clearSearchTextAndData(view: View?) {
        cancel.setOnClickListener {
            searchEditText.setText(UIConstants.EMPTY_STRING)
            genericAdapter.clearList(totalList)
        }
    }

    private fun registerLiveData() {
        searchViewModel.searchResponse.observe(viewLifecycleOwner, Observer { searchResponse ->
            searchResponse?.let { search ->
                if (!totalList.isNullOrEmpty()) {
                    genericAdapter.clearAndAddData(search.results)
                } else {
                    totalList.addAll(search.results)
                }
                initRecyclerview()
            }
        })

    }

    private fun initRecyclerview() {

        linearLayoutManager = LinearLayoutManager(MoveeApplication.appContext)
        searchReyclerview?.layoutManager = linearLayoutManager
        genericAdapter = GenericAdapter(this, totalList)
        searchReyclerview?.adapter = genericAdapter
    }

    private fun requestToTheService() {
        textWatcher = object : TextWatcher {
            private var searchFor = UIConstants.EMPTY_STRING
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim()
                if (searchText.length >= UIConstants.SIZE_LENGTH) {
                    if (searchText == searchFor)
                        return

                    searchFor = searchText

                    launch {
                        delay(UIConstants.DELAY_TIME)
                        if (searchText != searchFor)
                            return@launch

                        if (searchText.isNotEmpty()) {
                            searchViewModel.getSearchList(searchText, Locale.getDefault().language)


                        }

                    }
                }
            }

            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        }
    }

    override fun onItemClick(clickedItem: Any) {
        if (clickedItem is Search) {
            when (clickedItem.mediaType) {
                MediaType.MOVIE.type -> {
                    val movieModel = Movie(
                        clickedItem.adult,
                        clickedItem.backdropPath,
                        clickedItem.genreIds,
                        clickedItem.id,
                        clickedItem.originalLanguage,
                        clickedItem.originalTitle,
                        clickedItem.overview,
                        clickedItem.popularity,
                        clickedItem.posterPath,
                        clickedItem.releaseDate,
                        clickedItem.title,
                        clickedItem.video,
                        clickedItem.voteAverage,
                        clickedItem.voteCount
                    )

                    val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(movieModel)
                    Navigation.findNavController(requireView()).navigate(action)

                }
                MediaType.TV.type -> {
                    val tvSeries = TvSeries(
                        clickedItem.backdropPath,
                        clickedItem.firstAirDate,
                        clickedItem.genreIds,
                        clickedItem.id,
                        clickedItem.name,
                        clickedItem.originCountry,
                        clickedItem.originalLanguage,
                        clickedItem.originalName,
                        clickedItem.overview,
                        clickedItem.popularity,
                        clickedItem.posterPath,
                        clickedItem.voteAverage,
                        clickedItem.voteCount
                    )

                    val action = MainFragmentDirections.actionMainFragmentToTvSeriesDetailsFragment(tvSeries)
                    Navigation.findNavController(requireView()).navigate(action)

                }
                MediaType.PERSON.type -> {
                    val cast = Cast(
                        id = clickedItem.id,
                        name = clickedItem.name,
                        profilePath = clickedItem.profilePath
                    )

                    val action = MainFragmentDirections.actionMainFragmentToCastDetailsFragment(cast)
                    Navigation.findNavController(requireView()).navigate(action)

                }
            }
        }

    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main
}