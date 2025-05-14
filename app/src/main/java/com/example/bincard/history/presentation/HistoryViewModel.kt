package com.example.bincard.history.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bincard.history.domain.api.CardInfoInteractor
import com.example.bincard.history.presentation.model.CardInfoDBState
import com.example.bincard.search.domain.model.CardInformation
import com.example.bincard.search.presentation.mapper.CardInfoMapper
import com.example.bincard.search.presentation.model.CardInformationUI
import kotlinx.coroutines.launch

class HistoryViewModel(private val cardInfoInteractor: CardInfoInteractor) : ViewModel() {
    private val cardInfoStateLiveData = MutableLiveData<CardInfoDBState>()
    fun observeCardInfoState(): LiveData<CardInfoDBState> = cardInfoStateLiveData
    fun fillDataCardInfo() {
        viewModelScope.launch {
            cardInfoInteractor.getCardsInfo().collect { card ->
                processResult(card)
            }
        }
    }

    private fun processResult(cardList: List<CardInformation>) {
        if (cardList.isNotEmpty()) {
            renderState(CardInfoDBState.Content(convertCardInfoList(cardList)))
        } else {
            renderState(CardInfoDBState.Empty)
        }
    }

    private fun renderState(state: CardInfoDBState) {
        cardInfoStateLiveData.postValue(state)
    }

    private fun convertCardInfoList(cardInfoList: List<CardInformation>): List<CardInformationUI> {
        return cardInfoList.map { card -> CardInfoMapper.map(card) }
    }

    fun deleteCardInfo(cardInformationUI: CardInformationUI) {
        viewModelScope.launch {
            cardInfoInteractor.deleteCardInfo(CardInfoMapper.map(cardInformationUI))
            fillDataCardInfo()
        }
    }
}