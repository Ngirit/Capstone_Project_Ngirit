package com.example.capstoneprojectngirit.response

import com.example.capstoneprojectngirit.user.UserModel
import com.google.gson.annotations.SerializedName

data class MainResponse(
	@field:SerializedName("user")
	val user:ArrayList<UserModel>,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)
