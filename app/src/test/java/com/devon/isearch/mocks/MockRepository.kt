package com.devon.isearch.mocks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devon.isearch.model.types.Movie
import com.devon.isearch.repository.IRepository


// Essentially wraps a MutableList with the IRepository interface for testing purposes
class MockRepository(private val movies: MutableList<Movie>): IRepository {

    override fun getMoviesByPartialTitle(partial_title: String): LiveData<List<Movie>> {
        return MutableLiveData(movies.filter { it.title.startsWith(partial_title)})
    }

    override fun getMovieByTitle(title: String): LiveData<Movie> {
        TODO("mock not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}