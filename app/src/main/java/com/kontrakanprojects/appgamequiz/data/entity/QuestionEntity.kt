package com.kontrakanprojects.appgamequiz.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)val id: Int,
    val text: String,
    val answerKey: Int
)