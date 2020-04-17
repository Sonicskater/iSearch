package com.devon.isearch.datasource

import com.devon.isearch.datasource.types.MovieSearch
import com.devon.isearch.model.types.Movie
import com.google.gson.Gson
import org.koin.java.KoinJavaComponent.inject

class iTunesSource: IDataSource {

    private val requester by inject(IRequester::class.java)

    override fun getMoviesByName(name: String): List<Movie> {
        val rawData = requester.searchMovieJson(name)

        val gson = Gson()

        val parsed = gson.fromJson(rawData, MovieSearch::class.java)

        return parsed?.results?.map { data ->  Movie(data.trackName).apply{
            // undocumented endpoint to get higher res cover art
            url = data.artworkUrl100.replace("100x100bb.jpg","600x600bb.jpg")
            description = data.longDescription
            artist = data.artistName
            genre = data.primaryGenreName
            releaseYear = data.releaseDate.year + 1900 // 1900 needed to compute correct date
        } } ?: listOf()
    }
}