package com.kontrakanprojects.appgamequiz.data.dto

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("errors")
	val errors: Errors
)

data class Errors(

	@field:SerializedName("email")
	val email: List<String>,

//	@field:SerializedName("kata_sandi")
//	val kataSandi: List<String>
)
