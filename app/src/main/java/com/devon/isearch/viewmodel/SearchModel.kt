package com.devon.isearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Transformations

import com.devon.isearch.model.types.Movie
import com.devon.isearch.repository.IRepository
import kotlinx.coroutines.runBlocking
import org.koin.java.KoinJavaComponent.inject

class SearchModel : ISearchModel() {

    private val repository: IRepository by inject(IRepository::class.java)

    private val _searchString: MutableLiveData<String> = MutableLiveData("")

    override val movies: LiveData<out List<Movie>> = Transformations.switchMap(_searchString){
        if (it != "") {
            repository.getMoviesByPartialTitle(it)

        } else{
            // needs to contain an empty list, not null
            MutableLiveData(listOf())
        }
    }

    override var searchString: String
        get() = _searchString.value ?: ""
        set(value) {
            _searchString.value = value
        }
}