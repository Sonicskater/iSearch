package com.devon.isearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devon.isearch.model.Movie

class SearchModel : ViewModel(), ISearchModel {
    override val movies: LiveData<List<Movie>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override var searchString: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
}