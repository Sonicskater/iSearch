package com.devon.isearch

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.devon.isearch.service.ConnectivityChecker
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConnectivityCheckerTest {


    val context = InstrumentationRegistry.getInstrumentation().getTargetContext()

    // NOTE: Test is only valid if run from emulator with airplane mode on or data disabled. I cannot find a way to do this programatically for tests
    @Test
    fun connectivityTestOffline(){
        val x = ConnectivityChecker(context)
        assertFalse(x.checkConnectivity())
    }

    // NOTE: Test is only valid if run from emulator with airplane mode off or data enabled. I cannot find a way to do this programatically for tests
    @Test
    fun connectivityTestOnline(){
        val x = ConnectivityChecker(context)
        assertTrue(x.checkConnectivity())
    }
}
