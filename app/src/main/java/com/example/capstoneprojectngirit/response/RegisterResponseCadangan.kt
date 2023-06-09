package com.example.capstoneprojectngirit.response

import com.google.gson.annotations.SerializedName

data class RegisterResponseCadangan(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)
/*
{
	"success":true,
	"message": "Registration succeed",
	"data":{
		"id_user": "user_Hk4DtCp2BEm24hV5",
		"username": "bang alan",
		"email": "sialan@gmail.com"
		"password": "202cb962ac59075b964b07152d234b70"
	}
}

 */
