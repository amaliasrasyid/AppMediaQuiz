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
import timber.log.Timber


class GameQuestion internal constructor(text: String, screenX: Int, screenY: Int, question: Question,positionX: Float, res: Resources,densityDpi: Int) : GameComponent(res){

    private var text: String

    private var questionComponent: Bitmap
    private var questionWithTextCmpnt: Bitmap
    private var optionImgs: Array<OptionComponent?>
    private var densityDpi = 0

    private val TAG = GameQuestion::class.java.simpleName

    init {
        this.text = question.text
        this.densityDpi = densityDpi
        questionComponent = BitmapFactory.decodeResource(res, R.drawable.bg_question_game)

        Timber.d("Width Modal Quest : $width")
        Timber.d("Height Modal Quest : $height")

        width = (screenX/1.7).toFloat()
        height = if(densityDpi <= 200){
            (screenY/2.4).toFloat()
        }else{
            (screenY/2.7).toFloat()
        }


        Timber.d("Width Modal Quest After Adj: $width")
        Timber.d("Height Modal Quest After Adj: $height")

        questionComponent = Bitmap.createScaledBitmap(questionComponent, width.toInt(), height.toInt(), false)

        //posisi section questionComponent on game view
        x = positionX + (20 * screenRatioX)
        y = 25 * screenRatioY

        val density = res.displayMetrics.density
        this.text = text
        questionWithTextCmpnt = drawText(density)
        Timber.d("density drawtext: ${density}")


        //create list option components
        var marginX = 25f * screenRatioX
        val listOption = question.options
        optionImgs = arrayOfNulls(listOption.size)
        for((i,option) in listOption.withIndex()){
//            y+height
            val optionImg = OptionComponent(x+ (200 * screenRatioX),y+(questionWithTextCmpnt.height/2).toInt(),marginX,res,option.image!!)
            optionImgs[i] = optionImg
            marginX += (100f + 160) * screenRatioX
            Timber.d("Margin Between Options: $marginX")
        }

    }

    private fun drawText(density: Float) :Bitmap {
        val mutableBitmap = questionComponent.copy(Bitmap.Config.ARGB_8888,true)
        val canvas = Canvas(mutableBitmap)
        val paint = Paint()
        val textPaint = TextPaint().apply {
            textSize = if(densityDpi <= 200){ 13 * density }else{ 15 * density }
            typeface = Typeface.DEFAULT_BOLD
            color = Color.WHITE
        }
        Timber.d("Font Size Question: ${textPaint.textSize}")

        val bounds = canvas.clipBounds
        val slBuilder = StaticLayout.Builder.obtain(text,0,text.length,textPaint,canvas.width - (200 * screenRatioX).toInt())
            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
            .setMaxLines(2)
        val mTextLayout = slBuilder.build()

        val rect = Rect()
        paint.getTextBounds(text, 0, text.length, rect)
        Timber.d("Canvas Width: ${canvas.width}")

        canvas.save()
        val textX = if(densityDpi > 200){
            x - (190 * screenRatioX)
        }else{
            x - (270 * screenRatioX)
        }
        val textY = y + (15 * screenRatioY)

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