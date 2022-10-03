package com.kontrakanprojects.appgamequiz.data.repository

import androidx.lifecycle.LiveData
import com.kontrakanprojects.appgamequiz.data.dto.Resource
import com.kontrakanprojects.appgamequiz.data.dto.UserDTO
import com.kontrakanprojects.appgamequiz.data.request.LoginRequest
import com.kontrakanprojects.appgamequiz.data.source.remote.AuthRemoteResource

class AuthRepository() {
    private var _authRemoteDataSource = AuthRemoteResource()

    fun login(request: LoginRequest): LiveData<Resource<UserDTO>> = _authRemoteDataSource.login(request)

}