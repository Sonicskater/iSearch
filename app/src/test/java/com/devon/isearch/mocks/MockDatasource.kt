package com.devon.isearch.mocks

import com.devon.isearch.datasource.IDataSource
import com.devon.isearch.model.types.Movie

class MockDatasource(private val movies: List<Movie>): IDataSource {
    override fun getMoviesByName(name: String): List<Movie> {
        return movies.filter { it.name.startsWith(name) }
    }
}