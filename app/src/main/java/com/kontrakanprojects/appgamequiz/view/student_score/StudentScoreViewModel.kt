package com.kontrakanprojects.appgamequiz.view.student_score

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kontrakanprojects.appgamequiz.data.dto.Resource
import com.kontrakanprojects.appgamequiz.data.dto.StudentScore
import com.kontrakanprojects.appgamequiz.data.dto.StudentWithScore
import com.kontrakanprojects.appgamequiz.data.repository.StudentRepository
import com.kontrakanprojects.appgamequiz.data.request.StoreStudentScoreRequest
import com.kontrakanprojects.appgamequiz.view.quiz.QuizViewModel

class StudentScoreViewModel: ViewModel() {
    private  val repository =  StudentRepository()

    private val TAG = StudentScoreViewModel::class.java.simpleName

    fun getAll(): LiveData<Resource<List<StudentWithScore>>> = repository.getAllStudents()

}