package com.kontrakanprojects.appgamequiz.data.request

import com.kontrakanprojects.appgamequiz.util.Role

//NOTE! harus sama nama var dg nama param request di laravel
class RegisterRequest(
    val id: Int,
    val nama: String,
    val email: String,
    val kata_sandi: String,
    val role: Int
)