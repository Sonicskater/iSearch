package com.devon.isearch.datasource

import com.devon.isearch.model.types.Movie

interface IDataSource {
    fun getMoviesByName(name: String) : List<Movie>
}