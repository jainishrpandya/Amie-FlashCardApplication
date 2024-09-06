package com.example.newroomapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CardViewModel(private val repository: CardItemRepository): ViewModel()
{
    var cardItems: LiveData<List<CardItem>> = repository.allCardItems.asLiveData()

    fun addCardItems(newCard: CardItem) = viewModelScope.launch {
        repository.insertCardItem(newCard)
    }
    fun updateCardItems(newCard: CardItem) = viewModelScope.launch {
        repository.updateCardItem(newCard)
    }
    fun deleteCardItems(cardItem: CardItem) = viewModelScope.launch {
        repository.deleteCardItem(cardItem)
    }

}

public class CardItemModelFactory(private val repository: CardItemRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CardViewModel::class.java))
            return CardViewModel(repository) as T


        throw IllegalArgumentException("Unknown class for ViewModel")
    }
}