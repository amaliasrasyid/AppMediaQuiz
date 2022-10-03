package com.kontrakanprojects.appgamequiz.data.room

import androidx.room.*
import com.kontrakanprojects.appgamequiz.data.entity.OptionEntity
import com.kontrakanprojects.appgamequiz.data.entity.QuestionEntity
import com.kontrakanprojects.appgamequiz.data.entity.relationship.QuestionWithOptions

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestion(vararg questionEntity: QuestionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOptions(vararg optionEntity: OptionEntity)

    @Transaction
    @Query("SELECT * FROM questions")
    fun getQuestionWithOptions(): List<QuestionWithOptions>
}