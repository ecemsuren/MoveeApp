package com.example.moveeapp.data.components.network.repository.tmdb.api

import com.example.moveeapp.BuildConfig
import com.example.moveeapp.application.MoveeApplication
import com.example.moveeapp.data.components.network.repository.tmdb.base.IMoveeTmdbApiService
import com.example.moveeapp.data.components.network.repository.tmdb.base.MoveeTmdbApiConstants
import com.example.moveeapp.data.components.network.repository.tmdb.base.MoviesAndTvSeriesNetworkEndpoint
import com.example.moveeapp.data.components.network.repository.tmdb.model.*
import com.example.moveeapp.data.model.tmdb.CreateSession
import com.example.moveeapp.data.model.tmdb.FavoriteMovie
import com.example.moveeapp.data.model.tmdb.PostRate
import com.example.moveeapp.data.model.tmdb.UserInfo
import com.example.moveeapp.data.util.NetworkUtil
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TMDBApi {

    var context = MoveeApplication.appContext
    private val api: IMoveeTmdbApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(IMoveeTmdbApiService::class.java)

    }

    fun getMovies(page: Int, language: String, callBack: Callback<MovieResponse>) {
        if (NetworkUtil.isInternetAvailable(context)) {
            api.getPopularMovies(MoviesAndTvSeriesNetworkEndpoint.MOVIES.endpoint, BuildConfig.API_KEY, language,page)
                .enqueue(callBack)
        }
    }

    fun getGenreList(language: String, callBack: Callback<GenreListResponse>) {
        api.getMoviesGenres(MoviesAndTvSeriesNetworkEndpoint.GENRES.endpoint, BuildConfig.API_KEY, language)
            .enqueue(callBack)
    }
    fun getVisionsMovieList(language: String, callBack: Callback<VisionsMovieResponse>) {
        api.getVisionsFilms(MoviesAndTvSeriesNetworkEndpoint.VISIONS_FILMS.endpoint, BuildConfig.API_KEY, language)
            .enqueue(callBack)
    }

    fun getTopRatedTv(page: Int,language: String,callBack: Callback<TopRatedTvResponse>){
        api.getTopRatedTv(MoviesAndTvSeriesNetworkEndpoint.TOP_RATED_TV.endpoint, BuildConfig.API_KEY, language,page)
            .enqueue(callBack)
    }

    fun getPopularTvSeriesList(language: String,callBack: Callback<TvSeriesResponse>){
        api.getPopularTvSeries(MoviesAndTvSeriesNetworkEndpoint.POPULAR_TV_SERIES.endpoint, BuildConfig.API_KEY,language)
            .enqueue(callBack)
    }

    fun getTvSeriesGenreList(language: String,callBack: Callback<TvSeriesGenreResponse>) {
        api.getTvSeriesGenres(MoviesAndTvSeriesNetworkEndpoint.TV_SERIES_GENRE_LIST.endpoint,BuildConfig.API_KEY,language)
            .enqueue(callBack)
    }

    fun getMovieDetails(language: String,movieId: Int, callBack: Callback<MovieDetailsResponse>) {
        api.getMovieDetails(movieId,BuildConfig.API_KEY,language)
            .enqueue(callBack)
    }

    fun getTvSeriesDetails(language: String,tvSeriesId: Int, callBack: Callback<TvDetailsResponse>) {
        api.getTvDetails(tvSeriesId,BuildConfig.API_KEY,language)
            .enqueue(callBack)
    }

    fun getMovieCredit(movieId: Int, callBack: Callback<MovieCreditResponse>) {
        api.getMovieCredit(movieId)
            .enqueue(callBack)
    }

    fun getTvSeriesCredit(tvSeriesId: Int, callBack: Callback<TvCreditResponse>) {
        api.getTvSeriesCredit(tvSeriesId)
            .enqueue(callBack)
    }

    fun getPeopleDetails(language: String,personId: Int, callBack: Callback<PeopleDetailsResponse>) {
        api.getPeopleDetails(personId,BuildConfig.API_KEY,language)
            .enqueue(callBack)
    }

    fun getMoviesOfPeople(language: String,personId: Int, callBack: Callback<MoviesOfPeopleResponse>) {
        api.getMoviesOfPeople(personId,BuildConfig.API_KEY,language)
            .enqueue(callBack)
    }

    fun getSearch(language: String, callBack: Callback<SearchResponse>, query: String) {
        api.getSearch(MoviesAndTvSeriesNetworkEndpoint.SEARCH.endpoint, BuildConfig.API_KEY, query, language)
            .enqueue(callBack)
    }

    fun getGuest(callBack: Callback<GuestResponse>) {
        api.getGuest(MoviesAndTvSeriesNetworkEndpoint.GUEST.endpoint)
            .enqueue(callBack)
    }

    fun getCreateToken(callBack: Callback<RequestTokenResponse>) {
        api.getRequestToken(MoviesAndTvSeriesNetworkEndpoint.REQUEST_TOKEN.endpoint)
            .enqueue(callBack)
    }

    fun getSessionWithLogin(userInfo: UserInfo, callBack: Callback<SessionWithLoginResponse>) {
        api.postSessionIdWithLogin(MoviesAndTvSeriesNetworkEndpoint.SESSION_WITH_LOGIN.endpoint, userInfo, BuildConfig.API_KEY)
            .enqueue(callBack)
    }

    fun getCreateSession(request_token: CreateSession, callBack: Callback<CreateSessionResponse>) {
        api.postCreateSession(MoviesAndTvSeriesNetworkEndpoint.CREATE_SESSION.endpoint,request_token, BuildConfig.API_KEY)
            .enqueue(callBack)
    }

    fun getUserAccountDetails(callBack: Callback<AccountResponse>) {
        api.getAccountDetails(MoviesAndTvSeriesNetworkEndpoint.ACCOUNT.endpoint, BuildConfig.API_KEY, TMDBApiSessionSingleton.sessionId)
            .enqueue(callBack)
    }

    fun getFavoriteMovie(language: String,accountId: Int, callBack: Callback<MovieResponse>) {
        api.getFavoriteMovie(accountId, BuildConfig.API_KEY, TMDBApiSessionSingleton.sessionId, language)
            .enqueue(callBack)
    }

    fun postMarkFavoriteMovie(accountId: Int, markMovie: FavoriteMovie, callBack: Callback<MarkFavoriteResponse>) {
        api.postMarkFavoriteMovie(accountId, MoveeTmdbApiConstants.CONTENT_TYPE, markMovie, BuildConfig.API_KEY, TMDBApiSessionSingleton.sessionId)
            .enqueue(callBack)
    }

    fun getRateMovie(language: String,accountId: Int, callBack: Callback<MovieResponse>) {
        api.getRateMovie(accountId, BuildConfig.API_KEY, TMDBApiSessionSingleton.sessionId, language)
            .enqueue(callBack)
    }

    fun postRatingMovie(movieId: Int, value: PostRate, callBack: Callback<PostRateResponse>){
        api.postRateMovie(movieId,MoveeTmdbApiConstants.CONTENT_TYPE, value, BuildConfig.API_KEY, TMDBApiSessionSingleton.sessionId)
            .enqueue(callBack)
    }

    fun getRateTvSeries(language: String,accountId: Int, callBack: Callback<TvSeriesResponse>) {
        api.getRateTvSeries(accountId, BuildConfig.API_KEY, TMDBApiSessionSingleton.sessionId, language)
            .enqueue(callBack)
    }

    fun postRatingTvSeries(tvId: Int, value: PostRate, callBack: Callback<PostRateResponse>){
        api.postRateTvSeries(tvId,MoveeTmdbApiConstants.CONTENT_TYPE, value, BuildConfig.API_KEY, TMDBApiSessionSingleton.sessionId)
            .enqueue(callBack)
    }

}