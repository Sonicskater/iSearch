package com.devon.isearch

import android.app.Application
import com.devon.isearch.repository.IRepository
import com.devon.isearch.repository.Repository
import org.koin.core.context.startKoin
import org.koin.dsl.module

class iSearchApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}

var appModule = module {
    single<IRepository> {Repository()}
}