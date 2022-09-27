package com.kontrakanprojects.appgamequiz.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_scores")
data class StudentScoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val studentId: Int,
    val score: Int,
    val categoryId: Int
)