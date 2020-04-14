package com.devon.isearch

import android.app.Application
import com.devon.isearch.repository.IRepository
import com.devon.isearch.repository.Repository
import org.koin.core.context.startKoin
import org.koin.dsl.module

class iSearchApplication: Application() {
    var appModule = module {
        single<IRepository> {Repository()}
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}

