package com.example.bincard.search.data.network

import android.util.Log
import com.example.bincard.resources.data.ResourceProvider
import com.example.bincard.search.data.dto.Response
import com.example.bincard.search.data.dto.SearchRequest
import com.example.bincard.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class RetrofitNetworkClient(
    private val searchBin: SearchBin,
    private val resourceProvider: ResourceProvider
) : NetworkClient {
    override suspend fun doBinRequest(dto: Any): Response {
        return if (!resourceProvider.checkInternetConnection()) {
            Response().apply {
                result = Constants.CONNECTION_ERROR
            }
        } else {
            when (dto) {
                is SearchRequest -> doSearchRequest(dto)
                else -> {
                    Response().apply { result = Constants.NOT_FOUND }
                }
            }
        }
    }

    private suspend fun doSearchRequest(searchRequest: SearchRequest): Response {
        return withContext(Dispatchers.IO) {
            try {
                val searchResponse = searchBin.getBinInfo(searchRequest.expression)
                searchResponse.apply { result = Constants.SUCCESS }
            } catch (exceptionSearch: IOException) {
                Log.e(SEARCH_EXCEPTION, "$exceptionSearch")
                Response().apply { result = Constants.SERVER_ERROR }
            } catch (httpException: HttpException) {
                if (httpException.code() == Constants.TOO_MANY_REQUESTS) {
                    Response().apply { result = Constants.TOO_MANY_REQUESTS }
                } else {
                    Response().apply { result = Constants.SERVER_ERROR }
                }
            }
        }
    }

    companion object {
        const val SEARCH_EXCEPTION = "search_exception"
    }
}