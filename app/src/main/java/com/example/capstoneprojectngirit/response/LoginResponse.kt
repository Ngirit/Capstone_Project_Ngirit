package com.example.capstoneprojectngirit.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class LoginResult(
	@field:SerializedName("message")
	val message: String,
)
