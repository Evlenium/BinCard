package com.example.bincard.search.domain.api

import com.example.bincard.search.domain.model.CardInformation
import com.example.bincard.util.Resource
import kotlinx.coroutines.flow.Flow

interface SearchBinRepository {
    suspend fun searchBin(bin: String): Flow<Resource<CardInformation>>
}