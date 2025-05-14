package com.example.bincard.history.domain.use_case

import com.example.bincard.history.domain.api.CardInfoInteractor
import com.example.bincard.history.domain.api.CardInfoRepository
import com.example.bincard.search.domain.model.CardInformation
import kotlinx.coroutines.flow.Flow

class CardInfoInteractorImpl(private val cardInfoRepository: CardInfoRepository) :
    CardInfoInteractor {
    override suspend fun addCardInfo(cardInformation: CardInformation) {
        cardInfoRepository.addCardInfo(cardInformation)
    }

    override suspend fun deleteCardInfo(cardInfo: CardInformation) {
        cardInfoRepository.deleteCardInfo(cardInfo)
    }

    override suspend fun getCardsInfo(): Flow<List<CardInformation>> {
        return cardInfoRepository.getCardsInfo()
    }
}