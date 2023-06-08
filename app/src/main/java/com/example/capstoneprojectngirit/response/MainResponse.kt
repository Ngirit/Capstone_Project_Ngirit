package com.example.capstoneprojectngirit.response

import com.google.gson.annotations.SerializedName

data class MainResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)
