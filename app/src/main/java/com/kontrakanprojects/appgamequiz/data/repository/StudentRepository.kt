package com.kontrakanprojects.appgamequiz.data.repository

import androidx.lifecycle.LiveData
import com.kontrakanprojects.appgamequiz.data.dto.Resource
import com.kontrakanprojects.appgamequiz.data.dto.StudentScore
import com.kontrakanprojects.appgamequiz.data.dto.StudentWithScore
import com.kontrakanprojects.appgamequiz.data.request.StoreStudentScoreRequest
import com.kontrakanprojects.appgamequiz.data.source.remote.StudentRemoteResource

class StudentRepository() {
    private var _studentRemoteDataSource = StudentRemoteResource()

    fun getAllStudents(): LiveData<Resource<List<StudentWithScore>>> = _studentRemoteDataSource.getAllStudents()

    fun storeStudentScore(request: StoreStudentScoreRequest): LiveData<Resource<StudentScore>> = _studentRemoteDataSource.storeScore(request)

}