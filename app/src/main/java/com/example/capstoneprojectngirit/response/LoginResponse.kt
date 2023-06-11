package com.example.capstoneprojectngirit.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	//@field:SerializedName("loginResult")
	//val loginResult: LoginResult,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("userName")
	val userName: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("userId")
	val userId: String
)

data class LoginResult(
	@field:SerializedName("userName")
	val userName:String="",

	@field:SerializedName("userId")
	val userId:String="",
)


