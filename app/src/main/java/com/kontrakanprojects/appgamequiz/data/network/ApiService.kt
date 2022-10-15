package com.kontrakanprojects.appgamequiz.data.network

import com.kontrakanprojects.appgamequiz.data.dto.*
import com.kontrakanprojects.appgamequiz.data.request.LoginRequest
import com.kontrakanprojects.appgamequiz.data.request.RegisterRequest
import com.kontrakanprojects.appgamequiz.data.request.StoreStudentScoreRequest
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<AuthResponse>

    @POST("user/store")
    fun register(@Body request: RegisterRequest): Call<UserResponse>

    @FormUrlEncoded
    @GET("student/all")
    fun getAllStudents(): Call<StudentResponse> //TODO: blm diganti class response-nya

    @POST("student/store")
    fun storeScoreStudent(@Body request: StoreStudentScoreRequest): Call<StudentScoreResponse>
}