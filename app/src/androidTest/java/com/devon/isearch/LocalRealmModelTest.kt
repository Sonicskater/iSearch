package com.devon.isearch

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.annotation.UiThreadTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.devon.isearch.model.LocalRealmModel
import com.devon.isearch.model.types.Movie
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LocalRealmModelTest {
    lateinit var localRealmModel: LocalRealmModel
    lateinit var instrumentationContext: Context

    lateinit var config: RealmConfiguration;


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    @UiThreadTest
    fun setup(){
        Realm.init(InstrumentationRegistry.getInstrumentation().targetContext)
        config = RealmConfiguration.Builder().name("test-realm").build()
        Realm.deleteRealm(config)
        localRealmModel = LocalRealmModel(config)
    }

    @After
    @UiThreadTest
    fun cleanup(){

    }

    val test_data = listOf<Movie>(
        Movie("Antman"),
        Movie("Avengers"),
        Movie("Bee movie")
    )

    @Test
    @UiThreadTest
    fun addition(){
        runBlockingTest{
            assumeTrue(localRealmModel.moviesCount() == 0)
            localRealmModel.addMovies(test_data)
            assertTrue(localRealmModel.moviesCount() == test_data.size)
        }
        localRealmModel.cleanup()
    }

    @Test
    @UiThreadTest
    fun retreival(){
        assumeTrue(localRealmModel.moviesCount() == 0)
        runBlockingTest {
            localRealmModel.addMovies(test_data)
        }
        assumeTrue(localRealmModel.moviesCount() == test_data.size)
        val x = localRealmModel.getMoviesByPartialTitle("")
        x.observeForever {}
        assertEquals(test_data,x.value)

        localRealmModel.cleanup()
    }

    @Test
    @UiThreadTest
    fun caseInsensitive(){
        assumeTrue(localRealmModel.moviesCount() == 0)
        runBlockingTest {
            localRealmModel.addMovies(test_data)
        }
        assumeTrue(localRealmModel.moviesCount() == test_data.size)
        val x = localRealmModel.getMoviesByPartialTitle("a")
        x.observeForever {}
        assertEquals(listOf(
            Movie("Antman"),
            Movie("Avengers")
        ),x.value)

        localRealmModel.cleanup()
    }


}