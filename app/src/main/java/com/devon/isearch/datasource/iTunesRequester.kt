package com.devon.isearch.datasource

import com.devon.isearch.service.ConnectivityChecker
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.koin.java.KoinJavaComponent

class iTunesRequester : IRequester {

    private val connectivity: ConnectivityChecker by KoinJavaComponent.inject(
        ConnectivityChecker::class.java
    )

    override fun searchMovieJson(partialTitle: String): String {

        // check connectivity before request
        if (connectivity.checkConnectivity()) {
            val client = OkHttpClient.Builder().connectionSpecs(
                listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS)
            ).build()

            val httpUrl = "https://itunes.apple.com/search".toHttpUrlOrNull()!!.newBuilder()
                .addQueryParameter("entity","movie")
                .addQueryParameter("term",partialTitle)
                .addQueryParameter("limit", 20.toString())
            val request = Request.Builder()
                .url(httpUrl.build())
                .build()
            var response: Response? = null

            // still catch exceptions just in case
            try {
                response = client.newCall(request).execute()
            }
            finally {
                return response?.body?.string() ?: ""
            }

        }
        else {
            return ""
        }

    }
}