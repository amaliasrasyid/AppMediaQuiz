package com.kontrakanprojects.appgamequiz.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object ApiConfig {
    const val URL = "http://192.168.100.215:8000" //WIFI OFFICE
//    const val URL = "http://192.168.1.8:8000" //WIFI HOME
    const val ENDPOINT = "$URL/api/"

    var allowedClient = Interceptor{chain: Interceptor.Chain ->
        val response: Response
        val newRequest = chain.request().newBuilder()
            .addHeader("Accept","application/json")
            .method(chain.request().method,chain.request().body)
            .build()
        newRequest.headers["Cookie"]
        response = chain.proceed(newRequest)
        response.headers["Set-Cookie"]
        response
    }

    private fun client(): OkHttpClient{
        val loggingInterceptor =  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder().apply {
            readTimeout(200,TimeUnit.SECONDS)
            writeTimeout(600,TimeUnit.SECONDS)
            connectTimeout(600,TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            addInterceptor(allowedClient)
        }

        val client = builder.build()
        return client
    }

    fun create():ApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}