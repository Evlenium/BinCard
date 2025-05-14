package com.example.bincard.history.data.converters

import com.example.bincard.history.data.db.entity.CardInfoEntity
import com.example.bincard.search.domain.model.CardInformation

class CardInfoDBConverter {
    fun map(cardInfoEntity: CardInfoEntity): CardInformation {
        return CardInformation(
            cardBin = cardInfoEntity.cardBin,
            country = cardInfoEntity.country,
            coordinate = cardInfoEntity.coordinate,
            cardType = cardInfoEntity.cardType,
            bankURL = cardInfoEntity.bankURL,
            bankPhone = cardInfoEntity.bankPhone,
            baskSite = cardInfoEntity.baskSite,
            bankCity = cardInfoEntity.bankCity,
        )
    }

    fun map(cardInformation: CardInformation): CardInfoEntity {
        return CardInfoEntity(
            cardBin = cardInformation.cardBin,
            country = cardInformation.country,
            coordinate = cardInformation.coordinate,
            cardType = cardInformation.cardType,
            bankURL = cardInformation.bankURL,
            bankPhone = cardInformation.bankPhone,
            baskSite = cardInformation.baskSite,
            bankCity = cardInformation.bankCity,
        )
    }
}