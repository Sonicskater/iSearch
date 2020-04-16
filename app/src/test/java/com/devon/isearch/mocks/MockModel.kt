package com.devon.isearch.mocks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.switchMap
import com.devon.isearch.model.IModel
import com.devon.isearch.model.types.Movie

class MockModel(private val _movies: MutableLiveData<List<Movie>>) : IModel{
    override fun getMoviesByPartialTitle(title: String): LiveData<List<Movie>> {
        return Transformations.map(_movies){ it.filter { movie -> movie.name.startsWith(title) }}
    }

    override suspend fun addMovies(movies: List<Movie>) {
        val x = _movies.value?.toMutableList()
        x?.addAll(movies)
        _movies.value = x
    }

    override fun moviesCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}