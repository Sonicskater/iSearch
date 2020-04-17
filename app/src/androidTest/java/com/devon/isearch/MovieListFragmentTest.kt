package com.devon.isearch

import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devon.isearch.model.types.Movie
import com.devon.isearch.mocks.MockSearchModel
import com.devon.isearch.view.MovieCardAdapter
import com.devon.isearch.view.MovieListFragment
import com.devon.isearch.viewmodel.ISearchModel
import kotlinx.android.synthetic.main.test_text_view.view.*
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
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.nav_graph)

        val listScenario = launchFragmentInContainer<MovieListFragment>()

        listScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        onView(withId(R.id.movie_list)).check{ view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assumeTrue(view is RecyclerView)
            assumeTrue((view as RecyclerView).adapter != null)
            assertTrue(view.adapter != null && view.adapter?.itemCount == 0)
        }
    }

    @Test
    fun testInitialStateSearch(){
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.nav_graph)

        val listScenario = launchFragmentInContainer<MovieListFragment>()

        listScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        onView(withId(R.id.search_bar)).check{ view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assumeTrue(view is EditText)
            assumeTrue((view as EditText).text != null)
            assertTrue((view as EditText).text.toString() == "")
        }
    }

    @Test
    fun testChangeText(){
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.nav_graph)

        val listScenario = launchFragmentInContainer<MovieListFragment>()

        listScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }
        val test_string = "A"
        onView(withId(R.id.search_bar)).perform(click(), typeText(test_string)).check{ view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assumeTrue(view is EditText)
            assumeTrue((view as EditText).text != null)
            assertTrue((view as EditText).text.toString() == test_string)
        }
    }

    @Test
    fun testChangeTextSearch(){
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.nav_graph)

        val listScenario = launchFragmentInContainer<MovieListFragment>()

        listScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }
        val test_string = "A"
        onView(withId(R.id.movie_list)).check { view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assumeTrue(view is RecyclerView)
            assumeTrue((view as RecyclerView).adapter != null)
            assumeTrue((view).adapter!!.itemCount == 0)

        }

        onView(withId(R.id.search_bar)).perform(click(), typeText(test_string)).check{ view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assumeTrue(view is EditText)
            assumeTrue((view as EditText).text != null)
            assumeTrue((view as EditText).text.toString() == test_string)
        }

        onView(withId(R.id.movie_list)).check { view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assumeTrue(view is RecyclerView)
            assumeTrue((view as RecyclerView).adapter != null)
            assertTrue((view).adapter!!.itemCount == 1)

        }
    }

    @Test
    fun openMovie(){
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.nav_graph)

        val listScenario = launchFragmentInContainer<MovieListFragment>()

        listScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        onView(withId(R.id.search_bar)).perform(click(), typeText("A"))
        onView(withId(R.id.movie_list)).check { view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assumeTrue(view is RecyclerView)
            assumeTrue((view as RecyclerView).adapter != null)
            assumeTrue((view).adapter!!.itemCount == 1)

        }

        onView(withId(R.id.movie_list)).perform(RecyclerViewActions.actionOnItemAtPosition<MovieCardAdapter.MovieViewHolder>(0,click()))
        assertTrue(navController.currentDestination?.id == R.id.movieDetailFragment)
    }
}