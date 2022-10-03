package com.kontrakanprojects.appgamequiz.view.game

import androidx.lifecycle.ViewModel
import com.kontrakanprojects.appgamequiz.data.entity.OptionEntity
import com.kontrakanprojects.appgamequiz.data.entity.QuestionEntity
import com.kontrakanprojects.appgamequiz.data.entity.relationship.QuestionWithOptions
import com.kontrakanprojects.appgamequiz.data.repository.QuestionRepository

class GameViewModel(private val repository: QuestionRepository): ViewModel() {

//    fun getQuestions(): LiveData<List<QuestionWithOptions>> {
//        val result = MutableLiveData<List<QuestionWithOptions>>()
//        result.value = repository.getQuestions()
//        return  result
//    }
    fun getQuestions(): List<QuestionWithOptions> = repository.getQuestions()

    fun insertQuestion(question: QuestionEntity) =  repository.insertQuestion(question)

    fun insertOptions(vararg option: OptionEntity) =  repository.insertOptions(*option)



}