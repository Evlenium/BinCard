package com.example.bincard.search.presentation.mapper

import com.example.bincard.search.domain.model.CardInformation
import com.example.bincard.search.presentation.model.CardInformationUI

object CardInfoMapper {
    fun map(cardInformation: CardInformation): CardInformationUI {
        return CardInformationUI(
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