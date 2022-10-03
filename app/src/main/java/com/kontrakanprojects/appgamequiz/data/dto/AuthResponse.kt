package com.kontrakanprojects.appgamequiz.data.dto

import com.google.gson.annotations.SerializedName

data class AuthResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: UserDTO? = null,

	@field:SerializedName("message")
	val message: String? = null
)

