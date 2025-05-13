package com.example.bincard.search.domain.impl

import com.example.bincard.search.domain.api.SearchBinInteractor
import com.example.bincard.search.domain.api.SearchBinRepository
import com.example.bincard.search.domain.model.CardInformation
import com.example.bincard.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchBinInteractorImpl(private val searchBinRepository: SearchBinRepository) :
    SearchBinInteractor {
    override suspend fun searchBin(bin: String): Flow<Pair<CardInformation?, String?>> {
        return searchBinRepository.searchBin(bin).map { result ->
            when (result) {
                is Resource.Success -> Pair(result.data, null)
                is Resource.Error -> Pair(null, result.message)
            }
        }
    }
}