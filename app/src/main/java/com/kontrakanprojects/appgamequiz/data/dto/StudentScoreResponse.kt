package com.kontrakanprojects.appgamequiz.data.dto

import com.google.gson.annotations.SerializedName

data class StudentScoreResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: StudentScore? = null,

	@field:SerializedName("message")
	val message: String? = null
)

