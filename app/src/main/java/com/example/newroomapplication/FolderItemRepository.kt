package com.example.newroomapplication

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class FolderItemRepository(private val taskItemDao: TaskItemDao)
{
    val allTaskItems: Flow<List<TaskItem>> = taskItemDao.allTaskItems()

    @WorkerThread
    suspend fun insertFolderItem(taskItem: TaskItem)
    {
        taskItemDao.insertFolderItem(taskItem)
    }

    @WorkerThread
    suspend fun updateFolderItem(taskItem: TaskItem)
    {
        taskItemDao.updateFolderItem(taskItem)
    }

    @WorkerThread
    suspend fun deleteFolderItem(taskItem: TaskItem)
    {
        taskItemDao.deleteFolderItem(taskItem)
    }
}