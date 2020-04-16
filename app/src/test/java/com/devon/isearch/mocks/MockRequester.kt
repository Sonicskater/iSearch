package com.devon.isearch.mocks

import com.devon.isearch.datasource.IRequester

class MockRequester(val fixedData: String): IRequester {
    override fun searchMovieJson(partialTitle: String): String {
        return fixedData
    }
}