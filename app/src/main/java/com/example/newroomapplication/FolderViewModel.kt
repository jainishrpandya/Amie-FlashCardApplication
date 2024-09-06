package com.example.newroomapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FolderViewModel(private val repository: FolderItemRepository): ViewModel()
{
    var taskItems: LiveData<List<TaskItem>> = repository.allTaskItems.asLiveData()

    fun addTaskItems(newTask: TaskItem) = viewModelScope.launch {
        repository.insertFolderItem(newTask)
    }

    fun updateTaskItems(taskItem: TaskItem) = viewModelScope.launch {
        repository.updateFolderItem(taskItem)
    }

    fun deleteTaskItems(taskItem: TaskItem) = viewModelScope.launch {
        repository.deleteFolderItem(taskItem)
    }

}

class FolderItemModelFactory(private val repository: FolderItemRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FolderViewModel::class.java))
            return FolderViewModel(repository) as T


        throw IllegalArgumentException("Unknown class for ViewModel")
    }
}