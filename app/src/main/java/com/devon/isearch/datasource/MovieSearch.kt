package com.devon.isearch.datasource

class MovieSearch {
    var resultCount = 0
    var results = mutableListOf<MovieEntry>()
}

class MovieEntry {
    var trackName : String = ""
}