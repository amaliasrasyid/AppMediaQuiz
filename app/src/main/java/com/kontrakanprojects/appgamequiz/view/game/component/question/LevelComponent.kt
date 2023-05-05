package com.kontrakanprojects.appgamequiz.view.game.component.question

import android.content.res.Resources
import android.graphics.*
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.Log
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.view.game.GameComponent
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioX
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioY

class LevelComponent constructor(level: Int,positionX: Float, positionY: Float, res: Resources) : GameComponent(res) {

    private var levelBitmap: Bitmap
    var level: Int = 1

    init{
        var bitmap = BitmapFactory.decodeResource(res,R.drawable.bg_level)

        //config img size
        width = bitmap.width.toFloat()
        height = bitmap.height.toFloat()
        
        //define position component on screen
        x = positionX
        y = positionY
        
        val density = res.displayMetrics.density
        this.level = level
        levelBitmap = drawText(bitmap,density)
    }

    private fun drawText(bitmap: Bitmap, density: Float) :Bitmap {
        val mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true)
        val canvas = Canvas(mutableBitmap)
        val paint = Paint()
        val textPaint = TextPaint().apply {
            textSize = 32 * density
            typeface = Typeface.DEFAULT_BOLD
            color = Color.WHITE
        }
        val text = level.toString()
        val slBuilder = StaticLayout.Builder.obtain(text,0,text.length,textPaint,canvas.width)
            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
            .setMaxLines(2)
        val mTextLayout = slBuilder.build()

        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)

        canvas.save()
        val textX = if(level < 10){
            x + (bitmap.width/2 - (73 * screenRatioX))
        } else {
            x + (bitmap.width/2 - (110 * screenRatioX))
        }
        val textY = y + (bitmap.height/4)

//        Log.d("LevelComponent","ukuran level component = ${bitmap.width/2}")
        Log.d("LevelComponent","text Xy = $textX;$textY")

        canvas.translate(textX, textY.toFloat())
        mTextLayout.draw(canvas)
        canvas.restore()

        return mutableBitmap
    }
    
    fun getLevel()  = levelBitmap

}