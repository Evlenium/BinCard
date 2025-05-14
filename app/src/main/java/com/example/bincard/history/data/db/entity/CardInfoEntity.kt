package com.example.bincard.history.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_info_table")
data class CardInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val cardBin: Int,
    @ColumnInfo
    val country: String,
    val coordinate: String,
    val cardType: String,
    val bankURL: String,
    val bankPhone: String,
    val baskSite: String,
    val bankCity: String
)
