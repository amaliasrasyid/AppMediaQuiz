package com.kontrakanprojects.appgamequiz.data.network

import com.kontrakanprojects.appgamequiz.data.dto.AuthResponse
import com.kontrakanprojects.appgamequiz.data.dto.UserDTO
import com.kontrakanprojects.appgamequiz.data.dto.UserResponse
import com.kontrakanprojects.appgamequiz.data.entity.StudentEntity
import com.kontrakanprojects.appgamequiz.data.request.LoginRequest
import com.kontrakanprojects.appgamequiz.data.request.RegisterRequest
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<AuthResponse>

    @POST("user/store")
    fun register(@Body request: RegisterRequest): Call<UserResponse>

    @FormUrlEncoded
    @GET("student")
    fun getAllStudents(): Call<StudentEntity> //TODO: blm diganti class response-nya

}