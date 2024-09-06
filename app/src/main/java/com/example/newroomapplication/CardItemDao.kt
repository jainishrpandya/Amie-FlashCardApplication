package com.example.newroomapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CardItemDao {
    @Query("SELECT * FROM card ORDER BY id ASC")
    fun allCardItems(): Flow<List<CardItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCardItem(cardItem: CardItem)

    @Update
    suspend fun updateCardItem(cardItem: CardItem)

    @Delete
    suspend fun deleteCardItem(cardItem: CardItem)


}