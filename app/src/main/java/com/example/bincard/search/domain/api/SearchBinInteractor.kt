package com.example.bincard.search.domain.api

import com.example.bincard.search.domain.model.CardInformation
import kotlinx.coroutines.flow.Flow

interface SearchBinInteractor {
    suspend fun searchBin(bin: String): Flow<Pair<CardInformation?, String?>>
}