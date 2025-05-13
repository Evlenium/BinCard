package com.example.bincard.search.data.network

import com.example.bincard.search.data.dto.Response

interface NetworkClient {
    suspend fun doBinRequest(dto: Any): Response
}