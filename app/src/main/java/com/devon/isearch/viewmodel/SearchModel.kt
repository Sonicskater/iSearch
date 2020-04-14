package com.devon.isearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devon.isearch.model.Movie

class SearchModel : ViewModel(), ISearchModel {
    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()

    // initialize LiveData, doesn't seem like i can provide a initial value in constructor
    init {
        _movies.value = listOf()
    }

    override val movies: LiveData<List<Movie>>
        get() = _movies

    override var searchString: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
}