package com.kontrakanprojects.appgamequiz.view.game.component.healthpoint

import android.content.res.Resources
import android.graphics.Rect
import com.kontrakanprojects.appgamequiz.view.game.GameComponent

class HealthPoints internal  constructor(screenX: Int, res: Resources, screenRatioX: Float,densityDpi: Int) :
    GameComponent(res) {

    private  var healthPoints: Array<HealthPoint?> = arrayOfNulls(3)

    init {
        var marginX = if(densityDpi <= 200){
            (50 * screenRatioX).toInt()
        }else{
            (200 * screenRatioX).toInt()
        }
        for(i in healthPoints.indices){
            val hp = HealthPoint(screenX,res,marginX,densityDpi)
            healthPoints[i] = hp
            marginX -= (100).toInt()
        }
    }

    fun getHealthPoints(): Array<HealthPoint?>{
        return healthPoints
    }

    fun getCollisionShape(): Rect {
        return Rect(x.toInt(), y.toInt(), (x + width).toInt(), (y + height).toInt())
    }

}