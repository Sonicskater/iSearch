package com.devon.isearch.datasource

import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

class iTunesRequester : IRequester {
    override fun searchMovieJson(partialTitle: String): String {


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
        try {
            response = client.newCall(request).execute()
        }
        finally {
            return response?.body?.string() ?: ""
        }
    }
}