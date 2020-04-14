package com.devon.isearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devon.isearch.datasource.IDataSource
import com.devon.isearch.mocks.MockDatasource
import com.devon.isearch.mocks.MockModel
import com.devon.isearch.mocks.MockRepository
import com.devon.isearch.model.IModel
import com.devon.isearch.model.types.Movie
import com.devon.isearch.repository.IRepository
import com.devon.isearch.repository.Repository
import com.devon.isearch.viewmodel.SearchModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
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
        startKoin {
            modules(mocks)
        }
    }

    @After
    fun cleanup(){
        // stop koin between tests to ensure clean slate
        stopKoin()
    }

    // define the mock repository for testing
    val mocks = module {
        single<IDataSource> {
            MockDatasource()
        }
        single<IModel> {
            MockModel()
        }
    }
}