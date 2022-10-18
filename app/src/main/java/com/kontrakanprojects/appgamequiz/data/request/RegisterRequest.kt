package com.kontrakanprojects.appgamequiz.data.request

//NOTE! harus sama nama var dg nama param request di laravel
class RegisterRequest(
    val id: Int,
    val nama: String,
    val kata_sandi: String,
    val role: Int
)