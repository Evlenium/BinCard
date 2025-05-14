package com.example.bincard.history.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bincard.history.data.db.entity.CardInfoEntity

@Dao
interface CardInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCardInfo(cardInfoEntity: CardInfoEntity)

    @Delete
    suspend fun deleteCardInfo(cardInfoEntity: CardInfoEntity)

    @Query("SELECT * FROM card_info_table")
    suspend fun getCardsInfo(): List<CardInfoEntity>
}
