package com.example.capstoneprojectngirit.api

import com.example.capstoneprojectngirit.response.LoginResponse
import com.example.capstoneprojectngirit.response.MainResponse
import com.example.capstoneprojectngirit.response.RegisteringResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

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

    @GET("user")
    fun getUser(
        @Header("Authorization") userId:String
    ):Call<MainResponse>

    @Multipart
    @POST("recomendation")
    fun upload(
        @Header("Authorization") userId:String,
        @Part("budget") budget:RequestBody,
        @Part("location") location:RequestBody,
        @Part("category") category:ResponseBody
    )

    @GET("recomendation")
    fun getRecomendation(
        @Header("Authorization") userId:String,
    )

}