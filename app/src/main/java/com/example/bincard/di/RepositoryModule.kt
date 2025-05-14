package com.example.bincard.di

import com.example.bincard.history.data.impl.CardInfoRepositoryImpl
import com.example.bincard.history.domain.api.CardInfoRepository
import com.example.bincard.resources.data.ResourceProvider
import com.example.bincard.search.data.impl.SearchBinRepositoryImpl
import com.example.bincard.search.domain.api.SearchBinRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ResourceProvider> {
        ResourceProvider(get(), get())
    }
    single<SearchBinRepository> {
        SearchBinRepositoryImpl(get(), get())
    }
    single<CardInfoRepository> {
        CardInfoRepositoryImpl(get(), get())
    }
}