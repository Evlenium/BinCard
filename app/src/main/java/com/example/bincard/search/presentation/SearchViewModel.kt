package com.example.bincard.search.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bincard.search.domain.api.SearchBinInteractor
import com.example.bincard.search.presentation.mapper.CardInfoMapper
import com.example.bincard.search.presentation.model.CardInfoState
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchBinInteractor: SearchBinInteractor,
) : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is dashboard Fragment"
//    }
//    val text: LiveData<String> = _text

    private val cardInfoLiveData = MutableLiveData<CardInfoState>()
    fun observeSearchState(): MutableLiveData<CardInfoState> = cardInfoLiveData

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
}