package com.kontrakanprojects.appgamequiz.view.game.component.fish

import android.content.res.Resources
import android.graphics.*
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.view.game.GameComponent
import com.kontrakanprojects.appgamequiz.view.game.GameView
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioX
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioY

class Fish(res: Resources,) : GameComponent(res) {
    var wasShot = true
    var speed = 20
    var fishCounter = 1
    var textNumber: String =""
        get()  = field
        set(value) {field = value}

    private var fish1: Bitmap
    private var fish2: Bitmap
    private var fish3: Bitmap
    private var fish4: Bitmap

    init {
        fish1 = BitmapFactory.decodeResource(res, R.drawable.fish1)
        fish2 = BitmapFactory.decodeResource(res, R.drawable.fish2)
        fish3 = BitmapFactory.decodeResource(res, R.drawable.fish3)
        fish4 = BitmapFactory.decodeResource(res, R.drawable.fish4)

        width = fish1.width.toFloat()
        height = fish1.height.toFloat()

        //reduces fish size
        width /= 2
        height /= 2

        //making size the fishes compatible with other devices
        width *= screenRatioX
        height *= screenRatioY

        //
        fish1 = Bitmap.createScaledBitmap(fish1, width.toInt(), height.toInt(), false)
        fish2 = Bitmap.createScaledBitmap(fish2, width.toInt(), height.toInt(), false)
        fish3 = Bitmap.createScaledBitmap(fish3, width.toInt(), height.toInt(), false)
        fish4 = Bitmap.createScaledBitmap(fish4, width.toInt(), height.toInt(), false)

        y = -height

    }

    //making fish like it moving
    fun getFish(): Bitmap {
        when(fishCounter) {
            1 -> {
                fishCounter++
                return fish1
            }
            2 -> {
                fishCounter++
                return fish2
            }
            3 -> {
                fishCounter++
                return fish3
            }
        }

        fishCounter = 1
        return fish4
    }

    fun getCollisionShape(): Rect {
        val left = x.toInt()
        val top = y.toInt()
        val right = (x + width).toInt()
        val bottom = (y + height).toInt()
        return Rect(left,top,right,bottom)
    }

    //return text that will drawing on Bitmap fish
    fun getTextShape(): Rect{
        val left = x.toInt()
        val top = y.toInt()
        val right = (x + width).toInt()
        val bottom = (y + height).toInt()
        return Rect(left,top,right,bottom)
    }

    fun drawTextOnTop(canvas: Canvas,res: Resources){
        val captionString = textNumber
        val paintText = Paint()

        val density =res.displayMetrics.density
        paintText.textSize = 28 * density
        paintText.typeface = Typeface.DEFAULT_BOLD

        val startPosition = x + (width / 2) - 25
        val endPosition = y + (height/2) + 20
        canvas.drawText(captionString, startPosition,endPosition,paintText)
    }
}