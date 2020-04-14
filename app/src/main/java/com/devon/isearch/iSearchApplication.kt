package com.devon.isearch

import android.app.Application
import com.devon.isearch.datasource.IDataSource
import com.devon.isearch.datasource.iTunesSource
import com.devon.isearch.model.IModel
import com.devon.isearch.model.RealmModel
import com.devon.isearch.repository.IRepository
import com.devon.isearch.repository.Repository
import org.koin.core.context.startKoin
import org.koin.dsl.module

// Override Application entry point so we can start Koin
class iSearchApplication: Application() {

    // Koin runtime configuration
    var appModule = module {
        single<IRepository> {Repository()}
        single<IDataSource> {iTunesSource()}
        single<IModel> {RealmModel()}
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}

