package com.devon.isearch

import com.devon.isearch.model.Movie
import com.devon.isearch.repository.IRepository

class MockRepository(private val movies: MutableList<Movie>): IRepository {
    override fun getMoviesByPartialTitle(partial_title: String): List<Movie> {
        return movies.filter { it.name.startsWith(partial_title) }
    }

    override fun getMovieByTitle(title: String): Movie {
        TODO("mock not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}