package com.devon.isearch.repository

import com.devon.isearch.model.types.Movie

interface IRepository {
    fun getMoviesByPartialTitle(partial_title: String): List<Movie>

    fun getMovieByTitle(title: String): Movie
}