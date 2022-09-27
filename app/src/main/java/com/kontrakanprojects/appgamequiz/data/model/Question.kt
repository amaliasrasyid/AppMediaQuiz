package com.kontrakanprojects.appgamequiz.data.model

class Question(
    val text: String,
    val answerKey: Int,
    val options: List<Option>,
    val type: Category = Category.GAME,
)