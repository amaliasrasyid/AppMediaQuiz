package com.kontrakanprojects.appgamequiz.view.game

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView
import androidx.core.content.ContextCompat.startActivity
import com.kontrakanprojects.appgamequiz.data.model.Question
import com.kontrakanprojects.appgamequiz.util.DataLocalDb
import com.kontrakanprojects.appgamequiz.util.calculateBaseSpeedNumber
import com.kontrakanprojects.appgamequiz.view.game.EndGameActivity.Companion.TYPE_GAME_OVER
import com.kontrakanprojects.appgamequiz.view.game.EndGameActivity.Companion.TYPE_GAME_SUCCESS
import com.kontrakanprojects.appgamequiz.view.game.EndGameActivity.Companion.TYPE_RESULT
import com.kontrakanprojects.appgamequiz.view.game.component.Background
import com.kontrakanprojects.appgamequiz.view.game.component.Bullet
import com.kontrakanprojects.appgamequiz.view.game.component.Flight
import com.kontrakanprojects.appgamequiz.view.game.component.fish.Fish
import com.kontrakanprojects.appgamequiz.view.game.component.question.GameQuestion
import com.kontrakanprojects.appgamequiz.view.game.component.question.LevelComponent
import java.util.*
import kotlin.collections.ArrayList


class GameView internal constructor(activity: Activity,context: Context, screenX: Int, screenY: Int, densityDpi: Int) :
    SurfaceView(context), Runnable {
    private var activity: Activity

    private var screenX = 0f
    private var screenY = 0f

    private lateinit var thread: Thread
    var isPlaying = false
    var isFinish = false
    private var isGameOver = false
    private var paint: Paint
    private var flight: Flight
    private var fishs: Array<Fish?>
    private lateinit var random: Random
    private var bullets: ArrayList<Bullet>
    private var background1: Background
    private var background2: Background
    private var currentAnswerKey: Int = 0
    private var indexGameQ: Int = 0

    private lateinit var question: Question
    private lateinit var questionCmp: GameQuestion
    private lateinit var levelComponent: LevelComponent

    private var isDataHaveLoaded = false

    private val TAG = GameView::class.java.simpleName
    private val SIZE_QUESTION = 20

    //kedua nilai ini didapat dr hasil uji dg ukuran layar yg digunakan sbg dasar untuk mendapatkankecepatan yg diinginkan
    private val BASE_MAXIMUM_SPEED_NUMBER = 30
    private val BASE_MINIMUM_SPEED_NUMBER = 10

    private val BASE_SCREEN_SIZE_X=1920f
    private val BASE_SCREEN_SIZE_Y=1080f

    private val MY_SCREEN_SIZE_X=2960f //2320f
    private val MY_SCREEN_SIZE_Y=1440f // 1080f

    private var screenAdjust = 0f

//    private var reduce

    companion object {
        //making compatible with other devices (on different screen sizes)
        var screenRatioX = 0f
        var screenRatioY = 0f
    }

    init {
        this.activity = activity
        this.screenX = screenX.toFloat()
        this.screenY = screenY.toFloat()

        when(densityDpi){
            in 1..120 -> screenAdjust = densityDpi.toFloat()/ 420f //ldpi
            in 160..213 -> screenAdjust = densityDpi.toFloat()/ 420f //mdpi
            in 213..240 -> screenAdjust = densityDpi.toFloat()/ 420f //hdpi
            in 240..320 -> screenAdjust = densityDpi.toFloat()/ 420f //xhdpi
            in 320..480 -> screenAdjust = densityDpi.toFloat()/ 420f  //xxhdpi
            in 480..640 -> screenAdjust = densityDpi.toFloat()/ 420f //xxxhdpi
        }
        screenRatioX = screenAdjust
        screenRatioY = screenAdjust

        Log.d(TAG,"screen ratio x = $screenRatioX")
        Log.d(TAG,"screen x = $screenX")
        Log.d(TAG,"screen adjust base number = $screenAdjust")

        background1 = Background(screenX, screenY, resources)
        background2 = Background(screenX, screenY, resources)

        flight = Flight(this, screenX, screenY, resources)
        bullets = arrayListOf()
        background2.x = screenX.toFloat()
        paint = Paint()

        // fishs syntax
        fishs = arrayOfNulls(4)
        for (i in fishs.indices) {
            val fish = Fish(resources)
            fish.textNumber = (i + 1).toString()
            fishs[i] = fish
        }
        random = Random()
        prepareQuestions()
    }

    fun prepareQuestions(){
        isDataHaveLoaded = true
        this.question = DataLocalDb.getQuestion(indexGameQ,resources)

        val index = indexGameQ

        //level
        levelComponent = LevelComponent(index + 1,80f,10f,resources)

        //question syntax
        val gameQuestion = GameQuestion(question.text,this.screenX.toInt(),this.screenY.toInt(),question,50f+levelComponent.width, resources)
        questionCmp = gameQuestion
        currentAnswerKey = question.answerKey

//        Log.d(TAG,"prepared soal:${questions.size}")
    }

    override fun run() {
        while (isPlaying) {
            update()
            draw()
            sleep()
        }
    }

    private fun update() {
        //Background movement speed
        background1.x -= 10 * screenRatioX
        background2.x -= 10 * screenRatioX

        if (background1.x + background1.background.width < 0) {
            background1.x = screenX
        }

        if (background2.x + background2.background.width < 0) {
            background2.x = screenX
        }

        if (flight.isGoingUp) {
            flight.y -= 20 * screenRatioY
        } else {
            flight.y += 20 * screenRatioY
        }

        if (flight.y < 0) {
            flight.y = 0F
        }

        if (flight.y >= screenY - flight.height) {
            flight.y = screenY - flight.height
        }

        val trash = arrayListOf<Bullet>()

        // bullets syntax line
        for (bullet in bullets) {
            if (bullet.x > screenX) {
                trash.add(bullet)
            }
            bullet.x += 50 * screenRatioX

            for (fish in fishs) {
                if (fish != null) {
                    // jika bersentuhan peluru dan burung maka burung mati
                    if (Rect.intersects(fish.getCollisionShape(), bullet.getCollisionShape())) {
                        fish.x = (-500).toFloat()
                        bullet.x = screenX + 500
                        fish.wasShot = true

                        //cek yg ditembak benar/tidak
                        if(fish.textNumber == currentAnswerKey.toString()){
                            //Todo: next question
                            if( indexGameQ < SIZE_QUESTION ) {
                                indexGameQ++
                                prepareQuestions()
                            }
                            else {
                                isPlaying = false
                                moveToResult()
                                Log.d(TAG,"Game finished,Good job!")
                            }

                        }else if(fish.textNumber != currentAnswerKey.toString()){
                            flight.reduceHp()
                            Log.d(TAG,"Salah Tembak,Good job!")

                        }
                    }
                }
            }
        }

        //CEk apa soal udah habis?
        if(!(indexGameQ < SIZE_QUESTION)) {
            isPlaying = false
            moveToResult()
            Log.d(TAG,"Game finished,Good job!")
        }

        //delete all bullets
        for (bullet in trash) {
            bullets.remove(bullet)
        }

        // movement fish (like moving it to the flight)
        for (fish in fishs) {
            if (fish != null) {
                fish.x -= fish.speed

                if (fish.x + fish.width < 0) {
                    // jika burung melewati lebar
//                    if (!fish.wasShot) {
//                        flight.reduceHp()
//                    }

//                  MAXIMUM SPEED BASE NUMBER
//                    val baseMinSpeed = 10.calculateBaseSpeedNumber(calculateRatio(screenX,screenRatioX))
//                    val baseMaxSpeed = 30.calculateBaseSpeedNumber(calculateRatio(screenX,screenRatioX))
                    val baseMinSpeed = 10
                    val baseMaxSpeed = 20
                    val bound = (baseMaxSpeed * screenRatioX).toInt()
                    Log.d(TAG,"bound = $bound")
                    fish.speed = random.nextInt(bound)

                    if (fish.speed < baseMinSpeed * screenRatioX) {
                        fish.speed = (baseMinSpeed * screenRatioX).toInt()
                    }
                    Log.d(TAG,"random speed = ${fish.speed}")


                    fish.x = screenX
                    fish.y = random.nextInt((screenY - fish.height).toInt()).toFloat()

//                    // jika burung melewati lebar
                    fish.wasShot = false
                }
            }
        }
    }

    private fun draw() {
        if (holder.surface.isValid) {
            val canvas = holder.lockCanvas()
            canvas.drawBitmap(background1.background, background1.x,background1.y,paint)
            canvas.drawBitmap(background2.background, background2.x,background2.y,paint)

            if(isDataHaveLoaded){
                val listHp = flight.healthPoints.getHealthPoints()
                for (hp in listHp){
                    if(hp != null) {
                        if(hp.isBroken){
                            canvas.drawBitmap(hp.getBrokenHealthPoint(), hp.x, hp.y, paint)
                        }else{
                            canvas.drawBitmap(hp.getHealthPoint(), hp.x, hp.y, paint)
                        }
                    }
                }

                //draw question and its option imgs
                //draw question component
                var questionComponent = questionCmp
                canvas.drawBitmap(questionComponent.getQuestionLayout(),questionComponent.x,questionComponent.y,paint)

                //draw options component
                val listOptions = questionComponent.getOptions()
//                Log.d(TAG,"size option soal-${indexGameQ} = ${listOptions.size}")
                for((index,option) in listOptions.withIndex()){
                    if(option != null){
                        option.textNumber = index + 1
                        canvas.drawBitmap(option.getOption(),option.x,option.y,paint)

                        //draw number (string) over it
                        option.drawTextOnTop(canvas,resources)
                    }
                }

                //draw level component
                val levelBitmap = levelComponent.getLevel()
                val levelComponent = levelComponent
                canvas.drawBitmap(levelBitmap,levelComponent.x,levelComponent.y,paint)

                if (flight.isDead) {
                    isPlaying = false
                    canvas.drawBitmap(flight.getDead(), flight.x, flight.y, paint)
                    holder.unlockCanvasAndPost(canvas)
                    moveToResult(true)
                    Log.d(TAG,"GAME OVER")
                    return
                }

                for (fish in fishs) {
                    if (fish != null) {
                        canvas.drawBitmap(fish.getFish(), fish.x, fish.y, paint)

                        //draw text on top fish
                        fish.drawTextOnTop(canvas,resources)

//                    Log.d(TAG,"bitmap w:${fish.width};h:${fish.height}")
                    }
                }

                canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint)
                for (bullet in bullets) {
                    canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint)
                }
            }
            holder.unlockCanvasAndPost(canvas)
        }
    }

    private fun moveToResult(isGameOver: Boolean = false) {
        val intent = Intent(activity,EndGameActivity::class.java)
        if(!isGameOver){
            intent.putExtra(TYPE_RESULT, TYPE_GAME_SUCCESS)
        }else{
            intent.putExtra(TYPE_RESULT, TYPE_GAME_OVER)

        }
        startActivity(context,intent,null)
        activity.finish()
    }


    private fun sleep() {
        Thread.sleep(17)
    }

    fun resume() {
        isPlaying = true
        thread = Thread(this)
        thread.start()
    }

    fun pause() {
        isPlaying = false
        thread.join()
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (event.x < screenX / 2) {
                    flight.isGoingUp = true

                    Log.d(TAG, "onTouchEvent: Pesawat Ditekan")
                }
            }
            MotionEvent.ACTION_UP -> {
                flight.isGoingUp = false
                if (event.x > screenX / 2) flight.toShoot++
                Log.d(TAG, "onTouchEvent: Peluru Ditekan")
            }
        }
        return true
    }

    /* saat animasi flight menembak selesai
        lanjutkan dengan menggambar peluru
     */
    fun newBullet() {
        val bullet = Bullet(resources)
        bullet.x = flight.x + flight.width
        bullet.y = flight.y + (flight.height / 2)
        bullets.add(bullet)
    }

    fun calculateRatio(screen: Float, screenRatio: Float): Float{
        return (screen/screenRatio).toFloat()
    }
}