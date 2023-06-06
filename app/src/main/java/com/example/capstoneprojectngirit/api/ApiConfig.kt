package com.example.capstoneprojectngirit.api

import com.example.capstoneprojectngirit.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    val mySecretKey=BuildConfig.KEY
    fun getApiService():ApiService{
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(mySecretKey).addConverterFactory(GsonConverterFactory.create()).client(client).build()
        return retrofit.create(ApiService::class.java)
    }
}