package com.devon.isearch.mocks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devon.isearch.model.types.Movie
import com.devon.isearch.viewmodel.ISearchModel

class MockSearchModel(private val all_movies: List<Movie>) :  ISearchModel(){
    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData(listOf())
    override val movies: LiveData<out List<Movie>>
        get() = _movies
    override var searchString: String = ""
        set(value) {
            field = value
            if (value.isNotEmpty()) {
                _movies.value = all_movies.filter { it.name.startsWith(value) }
            }else{
                _movies.value = listOf()
            }
        }

}