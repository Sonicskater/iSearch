package com.devon.isearch.repository

import androidx.lifecycle.LiveData
import com.devon.isearch.model.types.Movie

interface IRepository {
    fun getMoviesByPartialTitle(partial_title: String): LiveData<List<Movie>>

    fun getMovieByTitle(title: String): LiveData<Movie>
}