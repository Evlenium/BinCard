package com.example.bincard.search.data.network

import com.example.bincard.search.data.dto.SearchBinResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchBin {
    @GET("/{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): SearchBinResponse
}