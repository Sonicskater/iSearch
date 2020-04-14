package com.devon.isearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Transformations

import com.devon.isearch.model.types.Movie
import com.devon.isearch.repository.IRepository
import org.koin.java.KoinJavaComponent.inject

class SearchModel : ViewModel(), ISearchModel {

    private val repository: IRepository by inject(IRepository::class.java)

    private val _searchString: MutableLiveData<String> = MutableLiveData("")



    // initialize LiveData, doesn't seem like i can provide a initial value in constructor

    override val movies: LiveData<List<Movie>> = Transformations.switchMap(_searchString){
        repository.getMoviesByPartialTitle(it)
    }

    override var searchString: String
        get() = _searchString.value ?: ""
        set(value) {
            _searchString.value = value
        }
}