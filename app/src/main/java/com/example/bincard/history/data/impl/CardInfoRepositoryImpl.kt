package com.example.bincard.history.data.impl

import com.example.bincard.history.data.converters.CardInfoDBConverter
import com.example.bincard.history.data.db.CardInfoDataBase
import com.example.bincard.history.data.db.entity.CardInfoEntity
import com.example.bincard.history.domain.api.CardInfoRepository
import com.example.bincard.search.domain.model.CardInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardInfoRepositoryImpl(
    private val cardInfoDataBase: CardInfoDataBase,
    private val cardInfoDBConverter: CardInfoDBConverter
) : CardInfoRepository {
    override suspend fun addCardInfo(cardInformation: CardInformation) {
        cardInfoDataBase.cardInformationDao()
            .insertCardInfo(cardInfoDBConverter.map(cardInformation))
    }

    override suspend fun deleteCardInfo(cardInfo: CardInformation) {
        cardInfoDataBase.cardInformationDao().deleteCardInfo(cardInfoDBConverter.map(cardInfo))
    }

    override suspend fun getCardsInfo(): Flow<List<CardInformation>> = flow {
        val cardInfo = cardInfoDataBase.cardInformationDao().getCardsInfo()
        emit(convertCardInfoListEntity(cardInfo))
    }

    private fun convertCardInfoListEntity(cardInfoList: List<CardInfoEntity>): List<CardInformation> {
        return cardInfoList.map { card -> cardInfoDBConverter.map(card) }
    }
}