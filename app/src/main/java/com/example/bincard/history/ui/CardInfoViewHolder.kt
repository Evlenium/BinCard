package com.example.bincard.history.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bincard.databinding.InfoItemBinding
import com.example.bincard.search.presentation.model.CardInformationUI

class CardInfoViewHolder(
    itemView: View,
    private val cardInfoList: List<CardInformationUI>,
) :
    RecyclerView.ViewHolder(itemView) {
    private val binding = InfoItemBinding.bind(itemView)
    fun bindPosition(position: Int) {
        if (cardInfoList.isNotEmpty()) {
            with(binding) {
                textViewBin.text = "${cardInfoList[position].cardBin}"
                textViewCountry.text = cardInfoList[position].country
                textViewCoordinate.text = cardInfoList[position].coordinate
                textViewCardType.text = cardInfoList[position].cardType
                textViewBankURL.text = cardInfoList[position].bankURL
                textViewBankPhone.text = cardInfoList[position].bankPhone
                textViewBankSite.text = cardInfoList[position].baskSite
                textViewBankCity.text = cardInfoList[position].bankCity
            }
        }
    }
}