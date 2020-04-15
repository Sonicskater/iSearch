package com.devon.isearch

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devon.isearch.view.MovieListFragment
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Matchers.hasEntry
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assume.assumeTrue

@RunWith(AndroidJUnit4::class)
class MovieListFragmentTest {

    // Test that the recycler view is initialized correctly to an empty state
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