package com.kontrakanprojects.appgamequiz.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kontrakanprojects.appgamequiz.data.entity.OptionEntity
import com.kontrakanprojects.appgamequiz.data.entity.QuestionEntity
import com.kontrakanprojects.appgamequiz.data.entity.relationship.QuestionWithOptions
import com.kontrakanprojects.appgamequiz.data.model.Question
import com.kontrakanprojects.appgamequiz.data.request.StoreOption
import com.kontrakanprojects.appgamequiz.data.request.StoreQuestion

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestion(questionEntity: QuestionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOptions(vararg optionEntity: OptionEntity)

    @Transaction
    @Query("SELECT * FROM questions")
    fun getQuestionWithOptions(): List<QuestionWithOptions>
}