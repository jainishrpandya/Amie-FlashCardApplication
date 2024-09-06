package com.example.newroomapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskItemDao {
    @Query("SELECT * FROM folder ORDER BY id ASC")
    fun allTaskItems(): Flow<List<TaskItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolderItem(taskItem: TaskItem)

    @Update
    suspend fun updateFolderItem(taskItem: TaskItem)

    @Delete
    suspend fun deleteFolderItem(taskItem: TaskItem)
}