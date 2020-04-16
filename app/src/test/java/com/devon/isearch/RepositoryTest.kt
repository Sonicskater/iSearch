package com.devon.isearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devon.isearch.datasource.IDataSource
import com.devon.isearch.mocks.MockDatasource
import com.devon.isearch.mocks.MockModel
import com.devon.isearch.mocks.MockRepository
import com.devon.isearch.model.IModel
import com.devon.isearch.model.types.Movie
import com.devon.isearch.repository.IRepository
import com.devon.isearch.repository.Repository
import com.devon.isearch.viewmodel.SearchModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

class RepositoryTest: KoinTest {
    // jvmField needed otherwise the test runner gets upset: https://proandroiddev.com/fix-kotlin-and-new-activitytestrule-the-rule-must-be-public-f0c5c583a865
    // rule needed to mock the task executor for LiveData, otherwise throws fatal exception

    lateinit var repository: IRepository

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    // clear the model between runs
    @Before
    fun setup(){
        repository = Repository()

    }

    @After
    fun cleanup(){
        // stop koin between tests to ensure clean slate
        stopKoin()
    }

    // define the mock repository for testing


    @Test
    fun getMoviesFromNetwork(){
        val mocks = module {
            single<IDataSource> {
                MockDatasource(listOf(
                    Movie("AntMan"),
                    Movie("Bee Movie"),
                    Movie("Avengers")
                ))
            }
            single<IModel> {
                MockModel(MutableLiveData(listOf()))
            }
        }
        startKoin {
            modules(mocks)
        }
        lateinit var x: LiveData<List<Movie>>
        runBlockingTest {
            x = repository.getMoviesByPartialTitle("A")
            x.observeForever {  }
        }
        assertEquals(listOf(Movie("AntMan"),Movie("Avengers")),x.value)
    }

    @Test
    fun getMoviesFromLocal(){
        val mocks = module {
            single<IDataSource> {
                MockDatasource(listOf())
            }
            single<IModel> {
                MockModel(
                    MutableLiveData( listOf(
                    Movie("AntMan"),
                    Movie("Bee Movie"),
                    Movie("Avengers")
                )))
            }
        }
        startKoin {
            modules(mocks)
        }
        val x = repository.getMoviesByPartialTitle("A")
        x.observeForever {  }
        assertEquals(listOf(Movie("AntMan"),Movie("Avengers")),x.value)

    }
}