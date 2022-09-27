package com.kontrakanprojects.appgamequiz.view.game.component.healthpoint

import android.content.res.Resources
import android.graphics.Rect
import com.kontrakanprojects.appgamequiz.view.game.GameComponent

class HealthPoints internal  constructor(screenX: Int, res: Resources) :
    GameComponent(res) {

    private  var healthPoints: Array<HealthPoint?> = arrayOfNulls(3)

    init {
        var marginX = 200
        for(i in healthPoints.indices){
            val hp = HealthPoint(screenX,res,marginX)
            healthPoints[i] = hp
            marginX -= 100
        }
    }

    fun getHealthPoints(): Array<HealthPoint?>{
        return healthPoints
    }

    fun getCollisionShape(): Rect {
        return Rect(x.toInt(), y.toInt(), (x + width).toInt(), (y + height).toInt())
    }

}