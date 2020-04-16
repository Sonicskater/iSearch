package com.devon.isearch

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.devon.isearch.model.LocalRealmModel
import com.devon.isearch.model.types.Movie
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalRealmModelTest {
    lateinit var localRealmModel: LocalRealmModel
    lateinit var instrumentationContext: Context

    @Before
    fun setup(){
        Realm.init(InstrumentationRegistry.getInstrumentation().targetContext)
        val config: RealmConfiguration = RealmConfiguration.Builder().inMemory().name("test-realm").build()
        localRealmModel = LocalRealmModel(config)
    }

    val test_data = listOf<Movie>(
        Movie("Antman"),
        Movie("Avengers"),
        Movie("Bee movie")
    )

    @Test
    fun addition(){
        runBlocking{
            localRealmModel.addMovies(test_data)
            assertTrue(localRealmModel.moviesCount() == test_data.size)
        }
    }

    @Test
    fun retreival(){
        runBlocking{
            localRealmModel.addMovies(test_data)
            assumeTrue(localRealmModel.moviesCount() == test_data.size)
            assertEquals(test_data,localRealmModel.getMoviesByPartialTitle(""))
        }
    }


}