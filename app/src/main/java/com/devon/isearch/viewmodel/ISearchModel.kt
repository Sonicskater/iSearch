package com.devon.isearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devon.isearch.model.types.Movie

interface ISearchModel{
    val movies: LiveData<out List<Movie>>
    var searchString: String
}