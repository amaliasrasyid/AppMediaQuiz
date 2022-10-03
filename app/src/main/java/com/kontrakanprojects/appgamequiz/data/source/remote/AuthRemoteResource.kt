package com.kontrakanprojects.appgamequiz.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.kontrakanprojects.appgamequiz.data.dto.AuthResponse
import com.kontrakanprojects.appgamequiz.data.dto.ErrorResponse
import com.kontrakanprojects.appgamequiz.data.dto.Resource
import com.kontrakanprojects.appgamequiz.data.dto.UserDTO
import com.kontrakanprojects.appgamequiz.data.network.ApiConfig
import com.kontrakanprojects.appgamequiz.data.request.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRemoteResource() {
    private val TAG = AuthRemoteResource::class.java.simpleName

    fun login(request: LoginRequest): LiveData<Resource<UserDTO>>{
        val client = ApiConfig.create().login(request)
        val result = MutableLiveData<Resource<UserDTO>>()
        client.enqueue(object :Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, _response: Response<AuthResponse>) {
                val response = _response.body()
                if(response != null) {
                    result.postValue(Resource.success(response.data))
                }else{
                    val errResult = Gson().fromJson(_response.errorBody()?.charStream(),AuthResponse::class.java)
                    result.postValue(Resource.error(errResult.message,null))
                }
                Log.d(TAG,response.toString())
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                val errorMessage = t.message
                if(errorMessage != null) result.postValue(Resource.error(errorMessage,null))
                Log.d(TAG,t.message ?: "")
            }
        })

        return result
    }
}