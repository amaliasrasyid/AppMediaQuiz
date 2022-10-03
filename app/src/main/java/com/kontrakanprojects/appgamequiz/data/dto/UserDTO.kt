package com.kontrakanprojects.appgamequiz.data.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @field:SerializedName("role")
    val role: Int? = null,

    @field:SerializedName("nama")
    val nama: String? = null,

    @field:SerializedName("kata_sandi")
    val kataSandi: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("email")
    val email: String? = null
)
