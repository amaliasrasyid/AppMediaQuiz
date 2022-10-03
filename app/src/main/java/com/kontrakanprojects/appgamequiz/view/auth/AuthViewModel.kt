package com.kontrakanprojects.appgamequiz.view.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kontrakanprojects.appgamequiz.data.dto.Resource
import com.kontrakanprojects.appgamequiz.data.dto.UserDTO
import com.kontrakanprojects.appgamequiz.data.dto.UserResponse
import com.kontrakanprojects.appgamequiz.data.repository.AuthRepository
import com.kontrakanprojects.appgamequiz.data.request.LoginRequest

class AuthViewModel: ViewModel() {
    private  val repository =  AuthRepository()

    fun login(request: LoginRequest): LiveData<Resource<UserDTO>> = repository.login(request)
}