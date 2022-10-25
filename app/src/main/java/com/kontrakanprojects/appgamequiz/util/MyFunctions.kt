package com.kontrakanprojects.appgamequiz.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.databinding.FragmentRegisterBinding
import com.kontrakanprojects.appgamequiz.view.game.GameActivity

fun EditText.textTrim() = this.text.toString().trim()

fun View.mySnackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
}

fun converToBitmap(res: Resources,fileDrawable: Int): Bitmap {
    return BitmapFactory.decodeResource(res,fileDrawable)
}

fun getDisplayScreenSize(activity: GameActivity): Pair<Int,Int>{
    val wm = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val width: Int
    val height: Int
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = wm.currentWindowMetrics
        val windowInsets: WindowInsets = windowMetrics.windowInsets

        val insets = windowInsets.getInsetsIgnoringVisibility(
            WindowInsets.Type.navigationBars() or WindowInsets.Type.displayCutout())
        val insetsWidth = insets.right + insets.left
        val insetsHeight = insets.top + insets.bottom

        val b = windowMetrics.bounds
        width = b.width() - insetsWidth
        height = b.height() - insetsHeight
    } else {
        val size = Point()
        val display = wm.defaultDisplay // deprecated in API 30
        display?.getSize(size) // deprecated in API 30
        width = size.x
        height = size.y
    }
    return Pair(width,height)
}

fun Int.calculateBaseSpeedNumber(ratio: Float): Float{
    return this*ratio
}














