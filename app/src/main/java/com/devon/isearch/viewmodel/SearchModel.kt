package com.devon.isearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devon.isearch.model.Movie
import com.devon.isearch.repository.IRepository
import org.koin.java.KoinJavaComponent.inject

class SearchModel : ViewModel(), ISearchModel {

    private val repository: IRepository by inject(IRepository::class.java)

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()

    // initialize LiveData, doesn't seem like i can provide a initial value in constructor
    init {
        _movies.value = listOf()
    }

    override val movies: LiveData<List<Movie>>
        get() = _movies

    override var searchString: String = ""
        set(value) {
            field = value
            _movies.value = repository.getMoviesByPartialTitle(field)
        }
}