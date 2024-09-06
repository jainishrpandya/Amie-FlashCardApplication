package com.example.newroomapplication

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class CardItemRepository(private val cardItemDao: CardItemDao) {

    val allCardItems: Flow<List<CardItem>> = cardItemDao.allCardItems()

    @WorkerThread
    suspend fun insertCardItem(cardItem: CardItem) {
        cardItemDao.insertCardItem(cardItem)
    }

    @WorkerThread
    suspend fun updateCardItem(cardItem: CardItem) {
        cardItemDao.updateCardItem(cardItem)
    }

    @WorkerThread
    suspend fun deleteCardItem(cardItem: CardItem) {
        cardItemDao.deleteCardItem(cardItem)
    }

}