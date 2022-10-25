package com.kontrakanprojects.appgamequiz.view.game.component.question

import android.content.res.Resources
import android.graphics.*
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.model.Question
import com.kontrakanprojects.appgamequiz.view.game.GameComponent
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioX
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioY


class GameQuestion internal constructor(text: String, screenX: Int, screenY: Int, question: Question,positionX: Float, res: Resources) : GameComponent(res){

    private var text: String

    private var questionComponent: Bitmap
    private var questionWithTextCmpnt: Bitmap
    private var optionImgs: Array<OptionComponent?>

    private val TAG = GameQuestion::class.java.simpleName

    init {
        this.text = question.text
        questionComponent = BitmapFactory.decodeResource(res, R.drawable.bg_question_game)

        width = (screenX/1.7).toFloat() + 50
        height = screenY/4.toFloat() + 130
        questionComponent = Bitmap.createScaledBitmap(questionComponent, width.toInt(), height.toInt(), false)

        //posisi section questionComponent on game view
        x = positionX - 25
        y = 25 * screenRatioY

        val density = res.displayMetrics.density
        this.text = text
        questionWithTextCmpnt = drawText(density)

        //create list option components
        var marginX = 25f
        val listOption = question.options
        optionImgs = arrayOfNulls(listOption.size)
        for((i,option) in listOption.withIndex()){
//            y+height
            val optionImg = OptionComponent(x+200,y+(questionWithTextCmpnt.height/2).toInt(),marginX,res,option.image!!)
            optionImgs[i] = optionImg
            marginX += 100f + 160
        }

    }

    private fun drawText(density: Float) :Bitmap {
        val mutableBitmap = questionComponent.copy(Bitmap.Config.ARGB_8888,true)
        val canvas = Canvas(mutableBitmap)
        val paint = Paint()
        val textPaint = TextPaint().apply {
            textSize = 16 * density
            typeface = Typeface.DEFAULT_BOLD
            color = Color.WHITE
        }

        val bounds = canvas.clipBounds
        val slBuilder = StaticLayout.Builder.obtain(text,0,text.length,textPaint,canvas.width-200)
            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
            .setMaxLines(2)
        val mTextLayout = slBuilder.build()

        val rect = Rect()
        paint.getTextBounds(text, 0, text.length, rect)

        canvas.save()
        val textX = x - 200
        val textY = y + 10

        canvas.translate(textX, textY.toFloat())
        mTextLayout.draw(canvas)
        canvas.restore()

        return mutableBitmap
    }

    fun getQuestionLayout() : Bitmap{
        return questionWithTextCmpnt
    }

    fun getOptions() : Array<OptionComponent?>{
        return optionImgs
    }

}