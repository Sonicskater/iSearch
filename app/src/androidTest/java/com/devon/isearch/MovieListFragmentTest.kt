package com.devon.isearch

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devon.isearch.model.types.Movie
import com.devon.isearch.mocks.MockSearchModel
import com.devon.isearch.view.MovieListFragment
import com.devon.isearch.viewmodel.ISearchModel
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assume.assumeTrue
import org.junit.Before

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class MovieListFragmentTest {

    // Test that the recycler view is initialized correctly to an empty state
    @Before
    fun setup(){
        stopKoin()
        startKoin {
            modules(module {
                viewModel<ISearchModel> { MockSearchModel(
                    mutableListOf(
                        Movie("Antman")
                    )
                ) }
            })
        }
    }
    @Test
    fun testInitialState(){
        val scenario = launchFragmentInContainer<MovieListFragment>(fragmentArgs = null, factory = null)

        onView(withId(R.id.movie_list)).check{ view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assumeTrue(view is RecyclerView)
            assumeTrue((view as RecyclerView).adapter != null)
            assertTrue(view.adapter != null && view.adapter?.itemCount == 0)
        }
    }
}