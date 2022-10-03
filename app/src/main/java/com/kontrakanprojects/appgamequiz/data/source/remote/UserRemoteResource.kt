package com.kontrakanprojects.appgamequiz.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.kontrakanprojects.appgamequiz.data.dto.Resource
import com.kontrakanprojects.appgamequiz.data.dto.UserDTO
import com.kontrakanprojects.appgamequiz.data.dto.UserResponse
import com.kontrakanprojects.appgamequiz.data.network.ApiConfig
import com.kontrakanprojects.appgamequiz.data.request.RegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRemoteResource {
    private val TAG = UserRemoteResource::class.java.simpleName

    fun storeUser(request: RegisterRequest): LiveData<Resource<UserDTO>>{
        val client = ApiConfig.create().register(request)
        val result = MutableLiveData<Resource<UserDTO>>()
        result.value = Resource.loading(null)
        client.enqueue(object: Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, _response: Response<UserResponse>) {
                val response = _response.body()
                if(response != null) {
                    result.postValue(Resource.success(response.data))
                }else{
                    val errResult = Gson().fromJson(_response.errorBody()?.charStream(),UserResponse::class.java)
                    result.postValue(Resource.error(errResult.message,null))
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                val errorMessage = t.message
                if(errorMessage != null) result.postValue(Resource.error(errorMessage,null))
                Log.d(TAG,t.message ?: "")
            }
        })
        return result
    }
}