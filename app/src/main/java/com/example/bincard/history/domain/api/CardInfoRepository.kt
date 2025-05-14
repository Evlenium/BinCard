package com.example.bincard.history.domain.api

import com.example.bincard.search.domain.model.CardInformation
import kotlinx.coroutines.flow.Flow

interface CardInfoRepository {
    suspend fun addCardInfo(cardInformation: CardInformation)

    suspend fun deleteCardInfo(cardInfo: CardInformation)

    suspend fun getCardsInfo(): Flow<List<CardInformation>>
}