package com.example.newroomapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Card")
class CardItem (
    @ColumnInfo(name = "question") var question: String,
    @ColumnInfo(name = "answer") var answer: String,
    @ColumnInfo(name="folderId") var folderId: Int?,
    @ColumnInfo(name="nextReview") var nextReview: String?,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
){
}