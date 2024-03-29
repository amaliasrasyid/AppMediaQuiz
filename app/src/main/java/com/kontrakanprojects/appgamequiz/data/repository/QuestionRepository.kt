package com.kontrakanprojects.appgamequiz.data.repository

import com.kontrakanprojects.appgamequiz.data.entity.OptionEntity
import com.kontrakanprojects.appgamequiz.data.entity.QuestionEntity
import com.kontrakanprojects.appgamequiz.data.room.MyDatabase

class QuestionRepository(private val localDb: MyDatabase) {

    fun getQuestions() = localDb.questionDao().getQuestionWithOptions()

    fun insertQuestion(question: QuestionEntity) =  localDb.questionDao().insertQuestion(question)

    fun insertOptions(vararg option: OptionEntity) =  localDb.questionDao().insertOptions(*option)

}