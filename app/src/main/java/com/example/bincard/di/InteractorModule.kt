package com.example.bincard.di

import com.example.bincard.history.data.converters.CardInfoDBConverter
import com.example.bincard.history.domain.api.CardInfoInteractor
import com.example.bincard.history.domain.use_case.CardInfoInteractorImpl
import com.example.bincard.resources.domain.api.SharingInteractor
import com.example.bincard.resources.domain.use_case.SharingInteracrorImpl
import com.example.bincard.search.domain.api.SearchBinInteractor
import com.example.bincard.search.domain.impl.SearchBinInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    single<SearchBinInteractor> {
        SearchBinInteractorImpl(get())
    }
    factory { CardInfoDBConverter() }
    single<CardInfoInteractor> {
        CardInfoInteractorImpl(get())
    }
    single<SharingInteractor> {
        SharingInteracrorImpl(get())
    }
}