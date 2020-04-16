package com.devon.isearch.datasource

import okhttp3.ConnectionSpec
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request

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

        val response = client.newCall(request).execute()
        return response.body?.string() ?: ""
    }
}