package com.example.bincard.di

import com.example.bincard.search.domain.api.SearchBinInteractor
import com.example.bincard.search.domain.impl.SearchBinInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    single<SearchBinInteractor> {
        SearchBinInteractorImpl(get())
    }
}