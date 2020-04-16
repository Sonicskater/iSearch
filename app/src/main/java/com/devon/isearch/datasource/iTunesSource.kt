package com.devon.isearch.datasource

import com.devon.isearch.model.types.Movie

class iTunesSource: IDataSource {
    override fun getMoviesByName(name: String): List<Movie> {
        return listOf()
    }
}