package com.example.bincard.search.data.impl

import com.example.bincard.resources.data.ResourceProvider
import com.example.bincard.search.data.dto.SearchBinResponse
import com.example.bincard.search.data.dto.SearchRequest
import com.example.bincard.search.data.network.NetworkClient
import com.example.bincard.search.domain.api.SearchBinRepository
import com.example.bincard.search.domain.model.CardInformation
import com.example.bincard.util.Constants
import com.example.bincard.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchBinRepositoryImpl(
    private val client: NetworkClient,
    private val resourceProvider: ResourceProvider,
) : SearchBinRepository {
    override suspend fun searchBin(bin: String): Flow<Resource<CardInformation>> = flow {
        val response = client.doBinRequest(SearchRequest(bin))
        when (response.result) {
            Constants.SUCCESS -> emit(handleSuccessResponse(response as SearchBinResponse))
            Constants.NOT_FOUND -> emit(Resource.Error(resourceProvider.getErrorEmptyAnswer()))
            Constants.SERVER_ERROR -> emit(Resource.Error(resourceProvider.getErrorServer()))
            Constants.CONNECTION_ERROR -> emit(Resource.Error(resourceProvider.getErrorInternetConnection()))
        }
    }

    private fun handleSuccessResponse(response: SearchBinResponse): Resource.Success<CardInformation> {
        val vacancy = createCardInfoFromResponse(response)
        return Resource.Success(vacancy)
    }

    private fun createCardInfoFromResponse(response: SearchBinResponse) = CardInformation(
        country = response.country?.name ?: resourceProvider.getErrorUnknown(),
        coordinate = response.brand ?: resourceProvider.getErrorUnknown(),
        cardType = response.type ?: resourceProvider.getErrorUnknown(),
        bankURL = response.bank?.url ?: resourceProvider.getErrorUnknown(),
        bankPhone = response.bank?.phone ?: resourceProvider.getErrorUnknown(),
        baskSite = response.bank?.name ?: resourceProvider.getErrorUnknown(),
        bankCity = response.bank?.city ?: resourceProvider.getErrorUnknown(),
    )
}