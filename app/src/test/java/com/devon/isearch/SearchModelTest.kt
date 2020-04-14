package com.devon.isearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devon.isearch.viewmodel.ISearchModel
import com.devon.isearch.viewmodel.SearchModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class SearchModelTest {
    lateinit var searchModel: ISearchModel

    // jvmField needed otherwise the test runner gets upset: https://proandroiddev.com/fix-kotlin-and-new-activitytestrule-the-rule-must-be-public-f0c5c583a865
    // rule needed to mock the task executor for LiveData, otherwise throws fatal exception
    @Rule @JvmField
    var rule = InstantTaskExecutorRule()

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