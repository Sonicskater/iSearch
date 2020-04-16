package com.devon.isearch.model

import androidx.lifecycle.LiveData
import com.devon.isearch.model.types.Movie
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.RealmResults
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LocalRealmModel(private val realm_config: RealmConfiguration): IModel {

    private val instance = Realm.getInstance(realm_config)

    override fun getMoviesByPartialTitle(title: String): LiveData<List<Movie>> {
        lateinit var x: RealmResults<Movie>
        instance.executeTransaction {
             x = it.where(Movie::class.java).beginsWith("title",title).findAll()
        }
        return LiveRealmResults(x) as LiveData<List<Movie>>
    }

    override suspend fun addMovies(movies: List<Movie>) {
        Realm.getInstance(realm_config).use { realm ->
            realm.executeTransaction {
                it.insertOrUpdate(movies)
                val x = it.where(Movie::class.java).count().toInt()
            }
        }
    }

    override fun moviesCount(): Int {
        var x = 0
        Realm.getInstance(realm_config).use{
            it.executeTransaction {
                x = it.where(Movie::class.java).count().toInt()
            }
        }
        return x
    }

    fun cleanup(){
        instance.close()
    }
}