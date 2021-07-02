package com.example.moveeapp.data.components.network.repository.tmdb.base

import com.example.moveeapp.BuildConfig
import com.example.moveeapp.data.components.network.repository.tmdb.model.*
import com.example.moveeapp.data.model.tmdb.CreateSession
import com.example.moveeapp.data.model.tmdb.FavoriteMovie
import com.example.moveeapp.data.model.tmdb.PostRate
import com.example.moveeapp.data.model.tmdb.UserInfo
import retrofit2.Call
import retrofit2.http.*

interface  IMoveeTmdbApiService {

    @GET
    fun getPopularMovies (
        @Url endPoint: String,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String,
        @Query(MoveeTmdbApiConstants.LANGUAGE) language: String,
        @Query(MoveeTmdbApiConstants.PAGE_PARAMETER) page: Int
    ): Call<MovieResponse>

    @GET
    fun getMoviesGenres (
        @Url endPoint: String,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String,
        @Query(MoveeTmdbApiConstants.LANGUAGE) language: String
    ): Call<GenreListResponse>

    @GET
    fun getVisionsFilms (
        @Url endPoint: String,
        @Query (MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String = BuildConfig.API_KEY,
        @Query(MoveeTmdbApiConstants.LANGUAGE) language: String
    ): Call <VisionsMovieResponse>

    @GET
    fun getTopRatedTv(
        @Url endPoint: String,
        @Query (MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String,
        @Query(MoveeTmdbApiConstants.LANGUAGE) language: String,
        @Query(MoveeTmdbApiConstants.PAGE_PARAMETER) page: Int
    ): Call<TopRatedTvResponse>

    @GET
    fun getPopularTvSeries(
        @Url endPoint: String,
        @Query (MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String = BuildConfig.API_KEY,
        @Query(MoveeTmdbApiConstants.LANGUAGE) language: String
    ): Call<TvSeriesResponse>

    @GET
    fun getTvSeriesGenres (
        @Url endPoint: String,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String = BuildConfig.API_KEY,
        @Query(MoveeTmdbApiConstants.LANGUAGE) language: String
    ): Call<TvSeriesGenreResponse>


    //string dışında bunları başka türlü nasıl alacağımı bulamadım.

    @GET("movie/{movie_id}")
    fun getMovieDetails (
        @Path("movie_id") movieId: Int,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String = BuildConfig.API_KEY,
        @Query(MoveeTmdbApiConstants.LANGUAGE) language: String
    ): Call<MovieDetailsResponse>

    @GET("tv/{tv_id}")
    fun getTvDetails (
        @Path("tv_id") movieId: Int,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String = BuildConfig.API_KEY,
        @Query(MoveeTmdbApiConstants.LANGUAGE) language: String
    ): Call<TvDetailsResponse>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredit (
        @Path("movie_id") movieId: Int,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String = BuildConfig.API_KEY
    ): Call<MovieCreditResponse>

    @GET("tv/{tv_id}/credits")
    fun getTvSeriesCredit (
        @Path("tv_id") movieId: Int,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String = BuildConfig.API_KEY
    ): Call<TvCreditResponse>

    @GET("person/{person_id}")
    fun getPeopleDetails (
        @Path("person_id") personId: Int,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String = BuildConfig.API_KEY,
        @Query(MoveeTmdbApiConstants.LANGUAGE) language: String
    ): Call<PeopleDetailsResponse>

    @GET("person/{person_id}/movie_credits")
    fun getMoviesOfPeople (
        @Path("person_id") personId: Int,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String = BuildConfig.API_KEY,
        @Query(MoveeTmdbApiConstants.LANGUAGE) language: String
    ): Call<MoviesOfPeopleResponse>

    @GET
    fun getSearch (
        @Url endPoint: String,
        @Query (MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String,
        @Query(MoveeTmdbApiConstants.QUERY) query: String,
        @Query(MoveeTmdbApiConstants.LANGUAGE) language: String
    ): Call <SearchResponse>

    @GET
    fun getGuest (
        @Url endPoint: String,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String = BuildConfig.API_KEY
    ): Call<GuestResponse>

    @GET
    fun getRequestToken (
        @Url endPoint: String,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String = BuildConfig.API_KEY
    ): Call<RequestTokenResponse>

    @POST
    fun postSessionIdWithLogin(
        @Url endPoint: String,
        @Body userInfo: UserInfo,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String
    ): Call<SessionWithLoginResponse>

    @POST
    fun postCreateSession(
        @Url endPoint: String,
        @Body request_token: CreateSession,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String
    ): Call<CreateSessionResponse>

    @GET
    fun getAccountDetails (
        @Url endPoint: String,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String,
        @Query(MoveeTmdbApiConstants.SESSION_ID) sessionId: String
    ): Call<AccountResponse>

    @GET("account/{account_id}/favorite/movies")
    fun getFavoriteMovie (
        @Path("account_id") accountId: Int,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String,
        @Query(MoveeTmdbApiConstants.SESSION_ID) sessionId: String,
        @Query(MoveeTmdbApiConstants.LANGUAGE) language: String
    ): Call<MovieResponse>

    @POST("account/{account_id}/favorite")
    fun postMarkFavoriteMovie (
        @Path("account_id") accountId: Int,
        @Header ("Content-Type") contentType: String,
        @Body markMovie: FavoriteMovie,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String,
        @Query(MoveeTmdbApiConstants.SESSION_ID) sessionId: String
    ): Call<MarkFavoriteResponse>

    @GET("account/{account_id}/rated/movies")
    fun getRateMovie (
        @Path("account_id") accountId: Int,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String,
        @Query(MoveeTmdbApiConstants.SESSION_ID) sessionId: String,
        @Query(MoveeTmdbApiConstants.LANGUAGE) language: String
    ): Call<MovieResponse>

    @POST("movie/{movie_id}/rating")
    fun postRateMovie (
        @Path("movie_id") movieId: Int,
        @Header ("Content-Type") contentType: String,
        @Body value: PostRate,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String,
        @Query(MoveeTmdbApiConstants.SESSION_ID) sessionId: String
    ): Call<PostRateResponse>

    @GET("account/{account_id}/rated/tv")
    fun getRateTvSeries (
        @Path("account_id") accountId: Int,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String,
        @Query(MoveeTmdbApiConstants.SESSION_ID) sessionId: String,
        @Query(MoveeTmdbApiConstants.LANGUAGE) language: String
    ): Call<TvSeriesResponse>

    @POST("tv/{tv_id}/rating")
    fun postRateTvSeries (
        @Path("tv_id") tvId: Int,
        @Header ("Content-Type") contentType: String,
        @Body value: PostRate,
        @Query(MoveeTmdbApiConstants.API_KEY_PARAMETER) apiKey: String,
        @Query(MoveeTmdbApiConstants.SESSION_ID) sessionId: String
    ): Call<PostRateResponse>
}











