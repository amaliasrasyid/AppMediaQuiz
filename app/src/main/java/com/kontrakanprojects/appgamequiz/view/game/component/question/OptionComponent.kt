package com.kontrakanprojects.appgamequiz.view.game.component.question

import android.content.res.Resources
import android.graphics.*
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.Log
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.view.game.GameComponent
import com.kontrakanprojects.appgamequiz.view.game.GameView
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioX
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioY
import timber.log.Timber

class OptionComponent constructor(positionX: Float, positionY: Float, marginX: Float, res: Resources,bitmap: Bitmap) : GameComponent(res,bitmap) {

    private var optionImg: Bitmap
     var textNumber: Int = 0
    private var isChoosed = false

    init{
//        Log.d("option component","x = ${x};y = ${y}")

        width = 200f * screenRatioX
        height =  200f * screenRatioY

        Timber.d("Width Option After Adj: $width")
        Timber.d("Height Option After Adj: $height")

        //Define The max min size
        if(width < 40f){
            width = 40f
            height = 40f
        }

        Timber.d("Width Option Last: $width")
        Timber.d("Height Option Last: $height")
            optionImg = Bitmap.createScaledBitmap(bitmap,width.toInt(),height.toInt(),false)


        //option position on screen (question bitmap)
        x = positionX + marginX
        y = positionY - (30 * screenRatioX)
    }
    
    fun drawTextOnTop(canvas: Canvas,res: Resources){
        val captionString = textNumber.toString()
        val density =res.displayMetrics.density

        val paintText = Paint().apply {
            textSize = 28 * density
            typeface = Typeface.DEFAULT_BOLD
            color = Color.WHITE
        }

        val startPosition = x + (optionImg.width/2) - (15 * screenRatioX)
        val endPosition = y + (optionImg.height/2) + (15 * screenRatioX)
        canvas.drawText(captionString, startPosition,endPosition,paintText)
    }

    fun getOption() = optionImg

}