package com.kontrakanprojects.appgamequiz.view.game.component.healthpoint

import android.content.res.Resources
import com.kontrakanprojects.appgamequiz.view.game.GameView

abstract class HealthPointComponent(gameView: GameView, screenX: Int, res: Resources) {
    var x = 0F
    var y = 0F
    var width = 0F
    var height = 0F
}