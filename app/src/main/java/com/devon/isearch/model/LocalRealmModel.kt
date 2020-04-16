package com.devon.isearch.model

import androidx.lifecycle.LiveData
import com.devon.isearch.model.types.Movie
import io.realm.Realm
import io.realm.RealmConfiguration

class LocalRealmModel(private val realm_config: RealmConfiguration): IModel {

    override fun getMoviesByPartialTitle(title: String): LiveData<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun addMovies(movies: List<Movie>) {
        Realm.getInstance(realm_config).use { realm ->
            realm.executeTransaction {
                it.copyToRealmOrUpdate(movies)
            }
        }
    }

    override fun moviesCount(): Int {
        var x = 0
        Realm.getInstance(realm_config).use{
            it.executeTransaction {
                x = it.where(Movie::class.java).alwaysTrue().findAll().size
            }
        }
        return x
    }
}