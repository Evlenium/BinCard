package com.example.bincard.history.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.bincard.R
import com.example.bincard.search.presentation.model.CardInformationUI

class CardInfoAdapter(
    private val cardInfoList: List<CardInformationUI>,
    private val longClickListener: ItemLongClickListener
) :
    ListAdapter<CardInformationUI, CardInfoViewHolder>(Comparator()) {

    class Comparator : DiffUtil.ItemCallback<CardInformationUI>() {
        override fun areContentsTheSame(
            oldItem: CardInformationUI,
            newItem: CardInformationUI
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(
            oldItem: CardInformationUI,
            newItem: CardInformationUI
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: CardInfoViewHolder, position: Int) {
        holder.bindPosition(position)
        val item = cardInfoList[position]
        holder.itemView.setOnLongClickListener {
            longClickListener.onLongClickListener(item)
            true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.info_item, parent, false)
        return CardInfoViewHolder(view, cardInfoList)
    }

    fun interface ItemLongClickListener {
        fun onLongClickListener(card: CardInformationUI)
    }
}