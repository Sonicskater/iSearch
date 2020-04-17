package com.devon.isearch.service

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.ConnectionSpec
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.lang.Exception

class ConnectivityChecker(val context: Context) {

    // Unsure how to handle this in a multi-version enviroment, would prefer to use listeners if given the choice

    // used a random request to check connectivity, should be replaced with something better

    fun checkConnectivity() : Boolean{

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
       return activeNetwork?.isConnectedOrConnecting ?: false
    }
}