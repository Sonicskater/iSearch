package com.devon.isearch.viewmodel

import androidx.lifecycle.LiveData
import com.devon.isearch.model.types.Movie

interface ISearchModel{
    val movies: LiveData<List<Movie>>
    var searchString: String
}