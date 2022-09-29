package com.kontrakanprojects.appgamequiz.util

import android.content.Context
import android.widget.Toast

class Move (val context: Context) {
    fun moveToResult() =  Toast.makeText(context,"Pindah Ke halaman result", Toast.LENGTH_LONG).show()
}