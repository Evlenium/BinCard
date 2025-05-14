package com.example.bincard.history.presentation.model

import com.example.bincard.search.presentation.model.CardInformationUI

sealed interface CardInfoDBState {
    data class Content(val cardInfo: List<CardInformationUI>) : CardInfoDBState
    data object Empty : CardInfoDBState
}