package com.kontrakanprojects.appgamequiz.data.dto

import com.google.gson.annotations.SerializedName

data class StudentResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: List<StudentWithScore>,

	@field:SerializedName("message")
	val message: String
)

data class StudentScore(

	@field:SerializedName("nilai")
	val nilai: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("tipe_soal")
	val tipeSoal: Int,

	@field:SerializedName("id_siswa")
	val idSiswa: Int
)

data class StudentWithScore(

	@field:SerializedName("role")
	val role: Int,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("kata_sandi")
	val kataSandi: String,

	@field:SerializedName("student_score")
	val studentScore: StudentScore? = null,

	@field:SerializedName("id")
	val id: Int
)
