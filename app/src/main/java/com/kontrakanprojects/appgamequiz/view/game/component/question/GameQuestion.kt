package com.kontrakanprojects.appgamequiz.view.game.component.question

import android.content.res.Resources
import android.graphics.*
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import com.kontrakanprojects.appgamequiz.data.model.Question
import com.kontrakanprojects.appgamequiz.view.game.GameComponent
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioX
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioY


class GameQuestion internal constructor(text: String, screenX: Int, screenY: Int, question: Question,res: Resources) : GameComponent(res){

    private var images: Array<Bitmap?> = arrayOfNulls<Bitmap>(4)
    private var text: String

    private var questionComponent: Bitmap
    private var optionImgs: Array<OptionComponent?>

    private val TAG = GameQuestion::class.java.simpleName

    init {
        this.text = question.text
        questionComponent = Bitmap.createBitmap((screenX/1.7).toInt() ,screenY/4,Bitmap.Config.ALPHA_8)

        width = questionComponent.width.toFloat()
        height = questionComponent.height.toFloat()

        //posisi section questionComponent on game view
        x = 200 * screenRatioX //=> 64 pixel * screen ration
        y = 25 * screenRatioY

        val density = res.displayMetrics.density
        this.text = text
        val canvasText = drawText(density)

        var marginX = 25f
        val listOption = question.options
        optionImgs = arrayOfNulls(listOption.size)
        for((i,option) in listOption.withIndex()){
//            y+height
            val optionImg = OptionComponent(x+200,y+(canvasText.height/1.5).toInt(),marginX,res,option.image)
            optionImgs[i] = optionImg
            marginX += 100f + 120
        }
    }

    private fun drawText(density: Float) :Canvas {
        val canvas = Canvas(questionComponent)
        val paint = Paint()
        val textPaint = TextPaint().apply {
            textSize = 16 * density
            typeface = Typeface.DEFAULT_BOLD
        }
        val slBuilder = StaticLayout.Builder.obtain(text,0,text.length,textPaint,canvas.width)
            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
            .setMaxLines(2)
        val mTextLayout = slBuilder.build()

        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)

        canvas.save()
        val textX = x
        val textY = y

        canvas.translate(textX, textY.toFloat())
        mTextLayout.draw(canvas)
        canvas.restore()

        return canvas

//        canvas.drawText(text, x, y.toFloat(), paint)
    }

    fun getQuestionLayout() : Bitmap{
        return questionComponent
    }

    fun getOptions() : Array<OptionComponent?>{
        return optionImgs
    }

}