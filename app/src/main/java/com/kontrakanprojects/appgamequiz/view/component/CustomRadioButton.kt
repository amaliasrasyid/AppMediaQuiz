package com.kontrakanprojects.appgamequiz.view.component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.content.ContextCompat
import com.kontrakanprojects.appgamequiz.R

class CustomRadioButton: AppCompatCheckBox {
    private lateinit var image: Bitmap

    constructor(context: Context) : super(context) {init()}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){init()}
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr){init()}

    private fun init(){
        setBackgroundResource(R.drawable.shape_bg_subtitle)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun isChecked(): Boolean {
        return super.isChecked()
    }
}