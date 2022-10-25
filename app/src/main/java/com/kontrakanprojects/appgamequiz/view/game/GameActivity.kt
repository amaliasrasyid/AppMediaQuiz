package com.kontrakanprojects.appgamequiz.view.game

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.entity.OptionEntity
import com.kontrakanprojects.appgamequiz.data.entity.QuestionEntity
import com.kontrakanprojects.appgamequiz.data.model.Option
import com.kontrakanprojects.appgamequiz.data.model.Question
import com.kontrakanprojects.appgamequiz.data.repository.QuestionRepository
import com.kontrakanprojects.appgamequiz.data.room.MyDatabase
import com.kontrakanprojects.appgamequiz.util.DataLocalDb
import com.kontrakanprojects.appgamequiz.util.converToBitmap
import com.kontrakanprojects.appgamequiz.util.getDisplayScreenSize
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect

class GameActivity : AppCompatActivity() {

    private lateinit var gameView: GameView
    private lateinit var viewModel: GameViewModel
    private val TAG = GameActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val displaySize = getDisplayScreenSize(this)

        //density dpi
        val displayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(displayMetrics)

        var questions = ArrayList<Question>()
        gameView = GameView(this,this, displaySize.first,displaySize.second,displayMetrics.densityDpi)
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

//    suspend fun loadData(): ArrayList<Question> {
//        withContext(IO){
//            return DataLocalDb.getArrQuestions(resources)
//        }
//    }


    override fun onResume() {
        super.onResume()
        gameView.resume()
    }

    override fun onPause() {
        super.onPause()
        gameView.pause()
    }
}