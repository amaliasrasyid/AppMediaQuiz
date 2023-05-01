package com.kontrakanprojects.appgamequiz.view.game.component.healthpoint

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.view.game.GameComponent
import com.kontrakanprojects.appgamequiz.view.game.GameView
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioX
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioY
import timber.log.Timber

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

        Timber.d("Width HealthP : $width")
        Timber.d("Height HealthP : $height")

        if(width < 40f){
            width = 40f
            height = 49f
        }

        healthPoint = Bitmap.createScaledBitmap(healthPoint, width.toInt(), height.toInt(), false)
        brokenHp = Bitmap.createScaledBitmap(brokenHp, width.toInt(), height.toInt(), false)

        //health point position on screen (gameview)
        x = (screenX - (200 + marginX * screenRatioX)).toFloat() //lokasi di seperempat screen size
        y = 64 * screenRatioY
        Timber.d("Width HealthP After adjustment : $width")
        Timber.d("Height HealthP After adjustment: $height")
    }

    fun getHealthPoint(): Bitmap{
        return healthPoint
    }
    
    fun getBrokenHealthPoint(): Bitmap{
        return brokenHp
    }

}