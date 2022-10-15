package com.kontrakanprojects.appgamequiz.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.kontrakanprojects.appgamequiz.data.dto.*
import com.kontrakanprojects.appgamequiz.data.network.ApiConfig
import com.kontrakanprojects.appgamequiz.data.request.StoreStudentScoreRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentRemoteResource {
    private val TAG = StudentRemoteResource::class.java.simpleName

    fun getAllStudents(): LiveData<Resource<List<StudentWithScore>>>{
        val client = ApiConfig.create().getAllStudents()
        val result = MutableLiveData<Resource<List<StudentWithScore>>>()
        result.value = Resource.loading(null)
        client.enqueue(object :Callback<StudentResponse>{
            override fun onResponse(call: Call<StudentResponse>, _response: Response<StudentResponse>) {
                val response = _response.body()
                if(response != null) {
                    result.postValue(Resource.success(response.data))
                }else{
                    val errResult = Gson().fromJson(_response.errorBody()?.charStream(),
                        StudentResponse::class.java)
                    result.postValue(Resource.error(errResult.message,null))
                }
                Log.d(TAG,response.toString())
            }

            override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
                val errorMessage = t.message
                if(errorMessage != null) result.postValue(Resource.error(errorMessage,null))
                Log.d(TAG,t.message ?: "")
            }
        })
        return result
    }

    fun storeScore(request: StoreStudentScoreRequest): LiveData<Resource<StudentScore>>{
        val client = ApiConfig.create().storeScoreStudent(request)
        val result = MutableLiveData<Resource<StudentScore>>()
        result.value = Resource.loading(null)
        client.enqueue(object: Callback<StudentScoreResponse>{
            override fun onResponse(call: Call<StudentScoreResponse>, _response: Response<StudentScoreResponse>) {
                val response = _response.body()
                if(response != null) {
                    result.postValue(Resource.success(response.data))
                }else{
                    val errResult = Gson().fromJson(_response.errorBody()?.charStream(),StudentScoreResponse::class.java)
                    result.postValue(Resource.error(errResult.message,null))
                }
            }

            override fun onFailure(call: Call<StudentScoreResponse>, t: Throwable) {
                val errorMessage = t.message
                if(errorMessage != null) result.postValue(Resource.error(errorMessage,null))
                Log.d(TAG,t.message ?: "")
            }
        })
        return result
    }
}