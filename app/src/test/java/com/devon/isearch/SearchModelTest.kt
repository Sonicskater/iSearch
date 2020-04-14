package com.devon.isearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devon.isearch.model.Movie
import com.devon.isearch.repository.IRepository
import com.devon.isearch.viewmodel.ISearchModel
import com.devon.isearch.viewmodel.SearchModel
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

class SearchModelTest : KoinTest {
    lateinit var searchModel: ISearchModel

    // jvmField needed otherwise the test runner gets upset: https://proandroiddev.com/fix-kotlin-and-new-activitytestrule-the-rule-must-be-public-f0c5c583a865
    // rule needed to mock the task executor for LiveData, otherwise throws fatal exception
    @Rule @JvmField
    var rule = InstantTaskExecutorRule()

    // clear the model between runs
    @Before
    fun setup(){
        searchModel = SearchModel()
        startKoin {
            modules(mocks)
        }
    }

    @After
    fun cleanup(){
        // stop koin between tests to ensure clean slate
        stopKoin()
    }

    val mocks = module {
        single<IRepository> {
            MockRepository(
                mutableListOf(
                    Movie("Antman"),
                    Movie("Another Movie"),
                    Movie("Bee Movie")
                )
            )
        }
    }
    // Pre-conditions, if these fail rest of tests are nonsense as the viewmodel is in invalid state
    @Test
    fun initialStateIsEmpty() {
        assertTrue(searchModel.movies.value?.isEmpty() ?: false)
    }
    @Test
    fun initialSearchStringIsEmpty(){
        assertTrue(searchModel.searchString.isEmpty())
    }

    // Test basic search functionality
    @Test
    fun searchStringUpdatesList(){
        searchModel.searchString = "A"
        assertEquals(listOf(Movie("Antman"), Movie("Another Movie")),searchModel.movies.value)
    }

    // Tests if data updates correctly as field is "typed" in
    @Test
    fun searchStringUpdatesListReactive(){
        searchModel.searchString = "A"
        assertEquals(listOf(Movie("Antman"), Movie("Another Movie")),searchModel.movies.value)

        searchModel.searchString = "An"
        assertEquals(listOf(Movie("Antman"), Movie("Another Movie")),searchModel.movies.value)

        searchModel.searchString = "Ant"
        assertEquals(listOf(Movie("Antman")),searchModel.movies.value)

        searchModel.searchString = "An"
        assertEquals(listOf(Movie("Antman"), Movie("Another Movie")),searchModel.movies.value)

        searchModel.searchString = "Ano"
        assertEquals(listOf(Movie("Another Movie")), searchModel.movies.value)
    }

    // Ensures screen clears when no query is entered
    @Test
    fun listClears(){
        searchModel.searchString = "A"
        assertEquals(listOf(Movie("Antman"), Movie("Another Movie")),searchModel.movies.value)

        searchModel.searchString = ""
        assertEquals(listOf<Movie>(), searchModel.movies.value)
    }

}