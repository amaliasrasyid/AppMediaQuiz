package com.kontrakanprojects.appgamequiz.data.entity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "options", foreignKeys = arrayOf(
    ForeignKey(
        entity = QuestionEntity::class,
        parentColumns = arrayOf("id") ,
        childColumns = arrayOf("questionId"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )
))
data class OptionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val image: Bitmap,
    val numberSequence: Int,
    val questionId: Int,
)