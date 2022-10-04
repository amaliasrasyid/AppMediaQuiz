package com.kontrakanprojects.appgamequiz.util

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.databinding.FragmentRegisterBinding

fun EditText.textTrim() = this.text.toString().trim()

fun View.mySnackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
}

fun converToBitmap(res: Resources,fileDrawable: Int): Bitmap {
    return BitmapFactory.decodeResource(res,fileDrawable)
}



