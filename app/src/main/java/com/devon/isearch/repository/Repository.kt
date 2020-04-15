package com.devon.isearch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.devon.isearch.datasource.IDataSource
import com.devon.isearch.model.IModel
import com.devon.isearch.model.types.Movie
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject

class Repository: IRepository {


    val remote_datasource: IDataSource by inject(IDataSource::class.java)

    val local_data: IModel by inject(IModel::class.java)

    override fun getMoviesByPartialTitle(partial_title: String): LiveData<List<Movie>> {
        GlobalScope.launch{
                val x = remote_datasource.getMoviesByName(partial_title)
                local_data.addMovies(x)
        }
        return local_data.getMoviesByPartialTitle(partial_title)
    }

    override fun getMovieByTitle(title: String): LiveData<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}