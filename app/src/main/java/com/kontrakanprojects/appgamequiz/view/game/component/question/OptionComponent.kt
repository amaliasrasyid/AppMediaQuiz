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

class OptionComponent constructor(qX: Float, qY: Float, marginX: Float, res: Resources,bitmap: Bitmap) : GameComponent(res,bitmap) {

    private var optionImg: Bitmap
     var textNumber: Int = 0
    private var isChoosed = false

    init{
        //option position on screen (question bitmap)
        x = qX + marginX
        y = qY - 30

//        Log.d("option component","x = ${x};y = ${y}")

        width = bitmap.width.toFloat()
        height = bitmap.height.toFloat()

        optionImg = Bitmap.createScaledBitmap(bitmap,200,200,false)

    }
    
    fun drawTextOnTop(canvas: Canvas,res: Resources){
        val captionString = textNumber.toString()
        val density =res.displayMetrics.density

        val paintText = Paint().apply {
            textSize = 28 * density
            typeface = Typeface.DEFAULT_BOLD
            color = Color.WHITE
        }



        val startPosition = x + (optionImg.width) - 25
        val endPosition = y + 30
        canvas.drawText(captionString, startPosition,endPosition,paintText)

//        val text = textNumber.toString()
//        val paint = Paint()
//        val textPaint = TextPaint().apply {
//            textSize = 16 * density
//            typeface = Typeface.DEFAULT_BOLD
//        }
//        val slBuilder = StaticLayout.Builder.obtain(text,0,text.length,textPaint,canvas.width)
//            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
//            .setMaxLines(2)
//        val mTextLayout = slBuilder.build()
//
//        val bounds = Rect()
//        paint.getTextBounds(text, 0, text.length, bounds)
//
//        canvas.save()
//        val textX = x + optionImg.width
//        val textY = y
//
//        canvas.translate(textX, textY.toFloat())
//        mTextLayout.draw(canvas)
//        canvas.restore()
    }

    fun getOption() = optionImg

}