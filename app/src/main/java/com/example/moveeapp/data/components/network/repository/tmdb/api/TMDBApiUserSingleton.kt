package com.example.moveeapp.data.components.network.repository.tmdb.api

import com.example.moveeapp.data.components.network.base.ModelConstants

/**
 * Created by Ecem Suren on 3.11.2020.
 */
object TMDBApiUserSingleton {

    var accountId = ModelConstants.SINGLETON_ACCOUNT_ID
    var name = ModelConstants.EMPTY_STRING
    var userName = ModelConstants.EMPTY_STRING
    var isGuest: Boolean = false
}