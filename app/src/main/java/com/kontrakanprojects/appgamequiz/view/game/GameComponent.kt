package com.kontrakanprojects.appgamequiz.view.game

import android.content.res.Resources
import android.graphics.Bitmap

abstract class GameComponent( res: Resources,bitmap: Bitmap? = null) {
    var x = 0F
    var y = 0F
    var width = 0F
    var height = 0F

}