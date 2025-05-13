package com.example.bincard.search.presentation.model

interface CardInfoState {
    object Loading : CardInfoState
    data class Content(
        val data: CardInformationUI,
    ) : CardInfoState

    data class Error(
        val errorMessage: String,
    ) : CardInfoState
}