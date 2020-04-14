package com.devon.isearch.repository

import com.devon.isearch.datasource.IDataSource
import com.devon.isearch.model.IModel
import com.devon.isearch.model.types.Movie
import org.koin.java.KoinJavaComponent.inject

class Repository: IRepository {


    val remote_datasource: IDataSource by inject(IDataSource::class.java)

    val local_data: IModel by inject(IModel::class.java)


    override fun getMoviesByPartialTitle(partial_title: String): List<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovieByTitle(title: String): Movie {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}