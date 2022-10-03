package com.kontrakanprojects.appgamequiz.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kontrakanprojects.appgamequiz.data.dto.Resource
import com.kontrakanprojects.appgamequiz.data.dto.UserDTO
import com.kontrakanprojects.appgamequiz.data.repository.UserRepository
import com.kontrakanprojects.appgamequiz.data.request.RegisterRequest

class ProfileViewModel: ViewModel() {
    private val repository = UserRepository()

    fun store(request: RegisterRequest): LiveData<Resource<UserDTO>> = repository.store(request)
}