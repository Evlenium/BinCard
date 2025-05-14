package com.example.bincard.history.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bincard.history.data.db.entity.CardInfoEntity

@Database(version = 1, entities = [CardInfoEntity::class], exportSchema = false)
abstract class CardInfoDataBase : RoomDatabase() {
    abstract fun cardInformationDao(): CardInfoDao
}