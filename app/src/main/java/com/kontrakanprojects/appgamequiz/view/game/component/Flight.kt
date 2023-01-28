package com.kontrakanprojects.appgamequiz.view.game.component

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.util.Log
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.view.game.GameComponent
import com.kontrakanprojects.appgamequiz.view.game.GameView
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioX
import com.kontrakanprojects.appgamequiz.view.game.GameView.Companion.screenRatioY
import com.kontrakanprojects.appgamequiz.view.game.component.healthpoint.HealthPoints

class Flight internal constructor(private val gameView: GameView, screenX: Int, screenY: Int, res: Resources): GameComponent(res) {

    var isGoingUp = false
    var isGoingDown = false
    var currentPosition = 0f
    var toShoot = 0
    private var wingCounter = 0
    private var shootCounter = 1

    private var flight1: Bitmap
    private var flight2: Bitmap

    private var shoot1: Bitmap
    private var shoot2: Bitmap
    private var shoot3: Bitmap
    private var shoot4: Bitmap
    private var shoot5: Bitmap

    private var dead: Bitmap
    var healthPoints: HealthPoints

    private var numFailure = 0
    var isDead = false

    private val TAG = Flight::class.java.simpleName


    init {
        flight1 = BitmapFactory.decodeResource(res, R.drawable.fly1)
        flight2 = BitmapFactory.decodeResource(res, R.drawable.fly2)

        //health point
        healthPoints = HealthPoints(screenX,res)
        numFailure = healthPoints.getHealthPoints().size - 1

        width = flight1.width.toFloat()
        height = flight1.height.toFloat()

        width /= 4
        height /= 4

        width *= screenRatioX
        height *= screenRatioY

        flight1 = Bitmap.createScaledBitmap(flight1, width.toInt(), height.toInt(), false)
        flight2 = Bitmap.createScaledBitmap(flight2, width.toInt(), height.toInt(), false)

        y = (screenY / 2).toFloat() //tepat di tengah vertikal (sumbu y)
        x = 64 * screenRatioX //=> 64 pixel * screen ration

        /* Shoot Code */
        shoot1 = BitmapFactory.decodeResource(res, R.drawable.shoot1)
        shoot2 = BitmapFactory.decodeResource(res, R.drawable.shoot2)
        shoot3 = BitmapFactory.decodeResource(res, R.drawable.shoot3)
        shoot4 = BitmapFactory.decodeResource(res, R.drawable.shoot4)
        shoot5 = BitmapFactory.decodeResource(res, R.drawable.shoot5)

        shoot1 = Bitmap.createScaledBitmap(shoot1, width.toInt(), height.toInt(), false)
        shoot2 = Bitmap.createScaledBitmap(shoot2, width.toInt(), height.toInt(), false)
        shoot3 = Bitmap.createScaledBitmap(shoot3, width.toInt(), height.toInt(), false)
        shoot4 = Bitmap.createScaledBitmap(shoot4, width.toInt(), height.toInt(), false)
        shoot5 = Bitmap.createScaledBitmap(shoot5, width.toInt(), height.toInt(), false)

        dead = BitmapFactory.decodeResource(res, R.drawable.dead)
        dead = Bitmap.createScaledBitmap(dead, width.toInt(), height.toInt(), false)

    }

    //memunculkan efek baling2 pesawat bergerak
    //dg memanggil yg satu setelahnya secara terus menerus
    fun getFlight(): Bitmap {
        /* Shoot */
        if (toShoot != 0) {
            when (shootCounter) {
                1 -> {
                    shootCounter++
                    return shoot1
                }
                2 -> {
                    shootCounter++
                    return shoot2
                }
                3 -> {
                    shootCounter++
                    return shoot3
                }
                4 -> {
                    shootCounter++
                    return shoot4
                }
                else -> {
                    shootCounter = 1
                    toShoot--
                    gameView.newBullet()
                    return shoot5
                }
            }
        }

        if (wingCounter == 0) {
            wingCounter++
            return flight1
        }

        wingCounter--
        return flight2
    }

    fun getCollisionShape(): Rect {
        return Rect(x.toInt(), y.toInt(), (x + width).toInt(), (y + height).toInt())
    }

    fun reduceHp(){
        Log.d(TAG,"${numFailure}")
        val sizeHp = healthPoints.getHealthPoints().size

        if(numFailure > -1 ){
            val healthPoint = healthPoints.getHealthPoints()[numFailure]
            if(healthPoint != null) healthPoint.isBroken = true
        }

        if(sizeHp != 1 && numFailure >= 1  ){
            numFailure--
        }else{
            isDead = true
        }
    }

    fun getDead(): Bitmap {
        return dead
    }
}