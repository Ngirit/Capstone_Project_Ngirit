package com.example.capstoneprojectngirit.api

import com.example.capstoneprojectngirit.response.LoginResponse
import com.example.capstoneprojectngirit.response.RegisteringResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun createAccount(
        @Field("username") username:String,
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<RegisteringResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<LoginResponse>
}