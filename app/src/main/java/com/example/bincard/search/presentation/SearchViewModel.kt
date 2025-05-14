package com.example.bincard.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bincard.history.domain.api.CardInfoInteractor
import com.example.bincard.search.domain.api.SearchBinInteractor
import com.example.bincard.search.presentation.mapper.CardInfoMapper
import com.example.bincard.search.presentation.model.CardInfoState
import com.example.bincard.search.presentation.model.CardInformationUI
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchBinInteractor: SearchBinInteractor,
    private val cardInfoInteractor: CardInfoInteractor
) : ViewModel() {
    private val cardInfoLiveData = MutableLiveData<CardInfoState>()
    fun observeSearchState(): LiveData<CardInfoState> = cardInfoLiveData
    fun searchBin(bin: String) {
        renderState(CardInfoState.Loading)
        viewModelScope.launch {
            searchBinInteractor
                .searchBin(bin)
                .collect { pair ->
                    if (pair.first != null) {
                        val currentCardInformation = CardInfoMapper.map(pair.first!!)
                        renderState(CardInfoState.Content(currentCardInformation))
                    }
                    when {
                        pair.second != null -> {
                            renderState(CardInfoState.Error(pair.second!!))
                        }
                    }
                }
        }
    }

    private fun renderState(state: CardInfoState) {
        cardInfoLiveData.postValue(state)
    }

    fun saveData(data: CardInformationUI) {
        viewModelScope.launch {
            cardInfoInteractor.addCardInfo(CardInfoMapper.map(data))
        }
    }
}