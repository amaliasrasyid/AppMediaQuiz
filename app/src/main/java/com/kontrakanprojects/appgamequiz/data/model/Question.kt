package com.kontrakanprojects.appgamequiz.data.model

import android.graphics.Bitmap

class Question(
    val text: String,
    val answerKey: Int,
    val options: List<Option>,
    val type: Category = Category.GAME,
    val competencyName: String = "",
    val images: List<Bitmap>? = null,
    val points: Int? = null
)
