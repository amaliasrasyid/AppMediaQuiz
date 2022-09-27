package com.kontrakanprojects.appgamequiz.view.game

import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView
import com.kontrakanprojects.appgamequiz.data.model.Question
import com.kontrakanprojects.appgamequiz.view.game.component.Background
import com.kontrakanprojects.appgamequiz.view.game.component.Bullet
import com.kontrakanprojects.appgamequiz.view.game.component.Flight
import com.kontrakanprojects.appgamequiz.view.game.component.fish.Fish
import com.kontrakanprojects.appgamequiz.view.game.component.question.GameQuestion
import java.util.*
import kotlin.collections.ArrayList


class GameView internal constructor(context: Context, screenX: Int, screenY: Int, questions: ArrayList<Question>) :
    SurfaceView(context), Runnable {

    private var screenX = 0f
    private var screenY = 0f

    private lateinit var thread: Thread
    private var isPlaying = false
    private var isGameOver = false
    private var paint: Paint
    private var flight: Flight
    private var fishs: Array<Fish?>
    private var random: Random
    private var bullets: ArrayList<Bullet>
    private var background1: Background
    private var background2: Background
    private var currentAnswerKey: Int = 0
    private var indexGameQ: Int = 0

    private var questionCmp = ArrayList<GameQuestion>()
    private var listQuestion = ArrayList<Question>()

    private val TAG = GameView::class.java.simpleName

//    private var reduce

    companion object {
        //making compatible with other devices (on different screen sizes)
        var screenRatioX = 0f
        var screenRatioY = 0f
    }

    init {
        this.listQuestion = questions

        this.screenX = screenX.toFloat()
        this.screenY = screenY.toFloat()
        screenRatioX = 1920f / screenX
        screenRatioY = 1080f / screenY

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

        //question syntax
        //initialize all game Question
        //TODO: BUG data list ini kosong tp pas draw ada (dugaan pas initialisasi tdk ada datanya blm
        for(question in questions){
            val gameQuestion = GameQuestion(question.text,screenX,screenY,question,resources)
            questionCmp.add(gameQuestion)
        }
        random = Random()

        if(questions.isNotEmpty()) currentAnswerKey = questions.get(0).answerKey  //SHOW NO DATA QUESTION
    }

    override fun run() {
        while (isPlaying) {
            update()
            draw()
            sleep()
        }
    }

    private fun update() {
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
                            if(listQuestion.size != 1 && indexGameQ < listQuestion.size ) {
                                indexGameQ++
                            }
                            else {
                                isPlaying = false
                                Log.d(TAG,"GAME SELESAI,Good job!")
                            }

                        }else if(fish.textNumber != currentAnswerKey.toString()){
                            flight.reduceHp()
                        }
                    }
                }
            }
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

                    val bound = (30 * screenRatioX).toInt()
                    fish.speed = random.nextInt(bound)

                    if (fish.speed < 10 * screenRatioX) {
                        fish.speed = (10 * screenRatioX).toInt()
                    }

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
            canvas.drawBitmap(
                background1.background,
                background1.x,
                background1.y,
                paint
            )
            canvas.drawBitmap(
                background2.background,
                background2.x,
                background2.y,
                paint
            )

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
            Log.d(TAG,"indexGameQ = ${indexGameQ}")
            if(listQuestion.isNotEmpty()){
                if(indexGameQ < listQuestion.size ){
                    currentAnswerKey = listQuestion.get(indexGameQ).answerKey
                    val questionComponent = questionCmp.get(indexGameQ)
                    canvas.drawBitmap(questionComponent.getQuestionLayout(),questionComponent.x,questionComponent.y,paint)
                    val listOptions = questionComponent.getOptions()
                    for((index,option) in listOptions.withIndex()){
                        if(option != null){
                            option.textNumber = index + 1
                            canvas.drawBitmap(option.getOption(),option.x,option.y,paint)

                            //draw number (string) over it
                            option.drawTextOnTop(canvas,resources)
                        }
                    }
                }

            }

            if (flight.isDead) {
                isPlaying = false
                canvas.drawBitmap(flight.getDead(), flight.x, flight.y, paint)
                holder.unlockCanvasAndPost(canvas)
                return
            }

            for (fish in fishs) {
                if (fish != null) {
                    canvas.drawBitmap(fish.getFish(), fish.x, fish.y, paint)

                    //draw text on top fish
                    fish.drawTextOnTop(canvas,resources)

                    Log.d(TAG,"bitmap w:${fish.width};h:${fish.height}")
                }
            }

            canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint)

            for (bullet in bullets) {
                canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint)
            }

            holder.unlockCanvasAndPost(canvas)
        }
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
}