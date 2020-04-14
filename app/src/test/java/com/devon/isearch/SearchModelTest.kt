package com.devon.isearch

import com.devon.isearch.viewmodel.ISearchModel
import com.devon.isearch.viewmodel.SearchModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class SearchModelTest {
    lateinit var searchModel: ISearchModel
    @Before
    fun setup(){
        searchModel = SearchModel()
    }

    // Pre-conditions, if these fail rest of tests are nonsense
    @Test
    fun initialStateIsEmpty() {
        assertTrue(searchModel.movies.value?.isEmpty() ?: false)
    }
    @Test
    fun initialSearchStringIsEmpty(){
        assertTrue(searchModel.searchString.isEmpty())
    }
}