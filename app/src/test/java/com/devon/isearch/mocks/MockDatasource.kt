package com.devon.isearch.mocks

import com.devon.isearch.datasource.IDataSource
import com.devon.isearch.model.types.Movie

class MockDatasource: IDataSource {
    override fun getMoviesByName(name: String): List<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}