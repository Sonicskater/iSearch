package com.devon.isearch.repository

import com.devon.isearch.model.Movie

class Repository: IRepository {
    override fun getMoviesByPartialTitle(partial_title: String): List<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovieByTitle(title: String): Movie {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}