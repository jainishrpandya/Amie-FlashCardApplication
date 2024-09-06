package com.example.newroomapplication

import android.app.Application

class TodoApplication(): Application()
{
    private val database1 by lazy {
        FolderItemDatabase.getDatabase(this)

    }
    private val database2 by lazy {
        CardItemDatabase.getDatabase(this)
    }

    val repository by lazy {
        FolderItemRepository(database1.taskItemDao())
    }

    val cardrepository by lazy {
        CardItemRepository(database2.cardItemDao())
    }

}