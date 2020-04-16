package com.devon.isearch.datasource

import com.devon.isearch.model.types.Movie
import com.google.gson.Gson
import org.koin.java.KoinJavaComponent.inject

class iTunesSource: IDataSource {

    private val requester by inject(IRequester::class.java)

    override fun getMoviesByName(name: String): List<Movie> {
        val data = requester.searchMovieJson(name)

        val gson = Gson()

        val parsed = gson.fromJson(data, MovieSearch::class.java)

        return parsed.results.map { Movie(it.trackName) }
    }
}