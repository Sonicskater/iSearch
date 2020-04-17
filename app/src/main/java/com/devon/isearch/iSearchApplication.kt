package com.devon.isearch

import android.app.Application
import com.devon.isearch.datasource.IDataSource
import com.devon.isearch.datasource.IRequester
import com.devon.isearch.datasource.iTunesRequester
import com.devon.isearch.datasource.iTunesSource
import com.devon.isearch.model.IModel
import com.devon.isearch.model.LocalRealmModel
import com.devon.isearch.repository.IRepository
import com.devon.isearch.repository.Repository
import com.devon.isearch.service.ConnectivityChecker
import com.devon.isearch.viewmodel.ISearchModel
import com.devon.isearch.viewmodel.SearchModel
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

// Override Application entry point so we can start Koin
class iSearchApplication: Application() {
    init {

    }



    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
        // Koin runtime configuration
        val appModule = module {
            single<IRepository> {Repository()}
            single<IDataSource> {iTunesSource()}
            single<IModel> {LocalRealmModel(RealmConfiguration.Builder().build())}
            single<IRequester> {iTunesRequester()}
            single{ ConnectivityChecker(this@iSearchApplication) }

            viewModel<ISearchModel> { SearchModel() }
        }
        startKoin {
            modules(appModule)
        }
    }
}

