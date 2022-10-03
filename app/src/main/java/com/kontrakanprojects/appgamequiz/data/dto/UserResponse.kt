package com.kontrakanprojects.appgamequiz.data.dto

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: UserDTO,

	@field:SerializedName("message")
	val message: String
)
