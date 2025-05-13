package com.example.bincard

import android.app.Application
import com.example.bincard.di.dataModule
import com.example.bincard.di.interactorModule
import com.example.bincard.di.repositoryModule
import com.example.bincard.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, repositoryModule, interactorModule, viewModelModule)
        }
    }
}