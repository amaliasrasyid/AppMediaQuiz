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

class HealthPoint(screenX: Int, res: Resources, marginX: Int = 0,densityDpi: Int) :
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
        Timber.d("density Dpi : $densityDpi")

        if(densityDpi > 200){
            if(width < 40f){
                width = 40f
                height = 49f
            }
        }


        healthPoint = Bitmap.createScaledBitmap(healthPoint, width.toInt(), height.toInt(), false)
        brokenHp = Bitmap.createScaledBitmap(brokenHp, width.toInt(), height.toInt(), false)

        //health point position on screen (gameview)
        //lokasi di seperempat screen size
        x = if( densityDpi <= 200){
            screenX - (80 + (marginX * screenRatioX))
        }else{
            screenX - (200 + (marginX * screenRatioX))
        }
//        val targetedLocation = (screenX/5).toFloat() //lokasi di seperempat screen size
//        x = screenX - (targetedLocation + (marginX * screenRatioX))
        y = 64 * screenRatioY
//        Timber.d("Width HealthP After adjustment : $width")
//        Timber.d("Height HealthP After adjustment: $height")
    }

    fun getHealthPoint(): Bitmap{
        return healthPoint
    }
    
    fun getBrokenHealthPoint(): Bitmap{
        return brokenHp
    }

}