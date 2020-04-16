package com.devon.isearch.model

import androidx.lifecycle.LiveData
import com.devon.isearch.model.types.Movie

interface IModel {
    fun getMoviesByPartialTitle(title: String): LiveData<List<Movie>>
    suspend fun addMovies(movies: List<Movie>)
    fun moviesCount(): Int
}