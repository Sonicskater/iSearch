package com.devon.isearch.datasource

interface IRequester {
    fun searchMovieJson(partialTitle: String) : String
}