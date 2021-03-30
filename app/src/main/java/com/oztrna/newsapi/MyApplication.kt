package com.oztrna.newsapi

import android.app.Application
import com.oztrna.newsapi.database.AppDatabase
import com.oztrna.newsapi.network.MyApi
import com.oztrna.newsapi.network.NewsRepository
import com.oztrna.newsapi.viewmodel.NewsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))


        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { NewsRepository(instance(), instance()) }
        bind() from provider { NewsViewModelFactory(instance()) }

    }

}