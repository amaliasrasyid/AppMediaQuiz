package com.kontrakanprojects.appgamequiz.data.entity.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.kontrakanprojects.appgamequiz.data.entity.OptionEntity
import com.kontrakanprojects.appgamequiz.data.entity.QuestionEntity


data class QuestionWithOptions(
    @Embedded val question: QuestionEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId",
        entity = OptionEntity::class
    )
    val options: List<OptionEntity>
)