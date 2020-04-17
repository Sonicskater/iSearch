package com.devon.isearch.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devon.isearch.model.types.Movie

abstract class ISearchModel: ViewModel(){
    abstract val movies: LiveData<out List<Movie>>
    abstract var searchString: String

    abstract fun connected() : Boolean
}