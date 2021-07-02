package com.example.moveeapp.ui.base

import com.example.moveeapp.data.model.tmdb.Genre

/**
 * Created by Ecem Suren on 1.10.2020.
 */

object GenreUtil {

    fun getGenres(genreIds: List<Int>, genreList: List<Genre>): String {
        val stringBuilder = StringBuilder()
        genreIds.let {
            it.forEach { currentGenre ->

                val currentGenreName = getNameOfGenre(currentGenre, genreList)

                currentGenreName.let { curName ->
                    if (stringBuilder.isNotEmpty()) {

                        stringBuilder.append(UIConstants.COMMA)
                    }
                    stringBuilder.append(curName)
                }
            }
        }
        return stringBuilder.toString()
    }

    private fun getNameOfGenre(id: Int, genreList: List<Genre>): String? {
        genreList.forEach { genre ->
            if (genre.id == id) {
                return genre.name
            }
        }
        return null
    }

}
