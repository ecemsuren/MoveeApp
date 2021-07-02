package com.example.moveeapp.data.components.network.repository.tmdb.base

enum class MoviesAndTvSeriesNetworkEndpoint (val endpoint: String) {
    MOVIES("movie/popular"),
    GENRES("genre/movie/list"),
    VISIONS_FILMS("movie/now_playing"),
    POPULAR_TV_SERIES("tv/popular"),
    TOP_RATED_TV("tv/top_rated"),
    TV_SERIES_GENRE_LIST("genre/tv/list"),
    SEARCH ("search/multi"),
    GUEST ("authentication/guest_session/new"),
    REQUEST_TOKEN("authentication/token/new"),
    SESSION_WITH_LOGIN("authentication/token/validate_with_login"),
    CREATE_SESSION("authentication/session/new"),
    ACCOUNT("account")

}