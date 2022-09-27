package com.kontrakanprojects.appgamequiz.view.game.component.healthpoint

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.view.game.GameComponent
import com.kontrakanprojects.appgamequiz.view.game.GameView
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioX
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioY

class HealthPoint(screenX: Int, res: Resources, marginX: Int = 0) :
    GameComponent(res) {

    private var healthPoint: Bitmap
    private var brokenHp: Bitmap
    var isBroken = false
        get() = field
        set(value) {field = value}

    init {
        healthPoint = BitmapFactory.decodeResource(res, R.drawable.ic_hp)
        brokenHp = BitmapFactory.decodeResource(res, R.drawable.ic_broken_hp)

        width = healthPoint.width.toFloat()
        height = healthPoint.height.toFloat()

        width *= screenRatioX
        height *= screenRatioY

        healthPoint = Bitmap.createScaledBitmap(healthPoint, width.toInt(), height.toInt(), false)
        brokenHp = Bitmap.createScaledBitmap(brokenHp, width.toInt(), height.toInt(), false)

        //health point position on screen (gameview)
        x = (screenX - (200 + marginX)).toFloat() //lokasi di seperempat screen size
        y = 64 * screenRatioY
    }

    fun getHealthPoint(): Bitmap{
        return healthPoint
    }
    
    fun getBrokenHealthPoint(): Bitmap{
        return brokenHp
    }

}