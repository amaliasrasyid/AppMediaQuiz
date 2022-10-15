package com.kontrakanprojects.appgamequiz.view.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kontrakanprojects.appgamequiz.data.dto.Resource
import com.kontrakanprojects.appgamequiz.data.dto.StudentScore
import com.kontrakanprojects.appgamequiz.data.repository.AuthRepository
import com.kontrakanprojects.appgamequiz.data.repository.StudentRepository
import com.kontrakanprojects.appgamequiz.data.request.StoreStudentScoreRequest
import com.kontrakanprojects.appgamequiz.data.source.remote.AuthRemoteResource

class QuizViewModel: ViewModel() {
    private  val repository =  StudentRepository()

    private val TAG = QuizViewModel::class.java.simpleName

    fun storeStudentScore(request: StoreStudentScoreRequest): LiveData<Resource<StudentScore>> = repository.storeStudentScore(request)

}