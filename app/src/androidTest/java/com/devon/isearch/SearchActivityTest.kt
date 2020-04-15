package com.devon.isearch

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.rule.ActivityTestRule

import com.devon.isearch.view.HostActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<HostActivity>
            = ActivityTestRule(HostActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.devon.isearch", appContext.packageName)
    }
}
