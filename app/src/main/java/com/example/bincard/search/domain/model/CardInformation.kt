package com.example.bincard.search.domain.model

data class CardInformation(
    val cardBin: Long,
    val country: String,
    val coordinate: String,
    val cardType: String,
    val bankURL: String,
    val bankPhone: String,
    val baskSite: String,
    val bankCity: String
)