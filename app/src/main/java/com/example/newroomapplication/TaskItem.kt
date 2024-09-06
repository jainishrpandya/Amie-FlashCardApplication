package com.example.newroomapplication

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Entity(tableName = "folder")
class TaskItem(
    @ColumnInfo(name = "folderName") var folderName: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
){

//    fun dueTime(): LocalTime? = if(dueTimeString == null) null
//        else LocalTime.parse(dueTimeString, timeFormatter)

    companion object{
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_TIME
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }

}