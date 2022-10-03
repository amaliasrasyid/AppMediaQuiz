package com.kontrakanprojects.appgamequiz.data.repository

import androidx.lifecycle.LiveData
import com.kontrakanprojects.appgamequiz.data.dto.Resource
import com.kontrakanprojects.appgamequiz.data.dto.UserDTO
import com.kontrakanprojects.appgamequiz.data.entity.OptionEntity
import com.kontrakanprojects.appgamequiz.data.entity.QuestionEntity
import com.kontrakanprojects.appgamequiz.data.request.RegisterRequest
import com.kontrakanprojects.appgamequiz.data.room.MyDatabase
import com.kontrakanprojects.appgamequiz.data.source.remote.UserRemoteResource

class UserRepository() {
    private var _userRemoteResource = UserRemoteResource()

    fun store(request: RegisterRequest): LiveData<Resource<UserDTO>> = _userRemoteResource.storeUser(request)

}