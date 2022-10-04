package com.kontrakanprojects.appgamequiz.view.game

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.entity.OptionEntity
import com.kontrakanprojects.appgamequiz.data.entity.QuestionEntity
import com.kontrakanprojects.appgamequiz.data.model.Option
import com.kontrakanprojects.appgamequiz.data.model.Question
import com.kontrakanprojects.appgamequiz.data.repository.QuestionRepository
import com.kontrakanprojects.appgamequiz.data.room.MyDatabase
import com.kontrakanprojects.appgamequiz.util.DataLocalDb
import com.kontrakanprojects.appgamequiz.util.converToBitmap

class GameActivity : AppCompatActivity() {

    private lateinit var gameView: GameView
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val point = Point()
        windowManager.defaultDisplay.getSize(point)

        var questions = DataLocalDb.getArrQuestions(resources)
        gameView = GameView(this,this, point.x, point.y,questions)

        setContentView(gameView)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }


    override fun onResume() {
        super.onResume()
        gameView.resume()
    }

    override fun onPause() {
        super.onPause()
        gameView.pause()
    }
}