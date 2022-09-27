package com.kontrakanprojects.appgamequiz.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "class")
data class ClassEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: Int,
)