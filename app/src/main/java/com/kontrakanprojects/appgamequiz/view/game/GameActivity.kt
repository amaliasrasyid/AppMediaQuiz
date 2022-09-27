package com.kontrakanprojects.appgamequiz.view.game

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.entity.OptionEntity
import com.kontrakanprojects.appgamequiz.data.entity.QuestionEntity
import com.kontrakanprojects.appgamequiz.data.model.Option
import com.kontrakanprojects.appgamequiz.data.model.Question
import com.kontrakanprojects.appgamequiz.data.repository.QuestionRepository
import com.kontrakanprojects.appgamequiz.data.request.StoreOption
import com.kontrakanprojects.appgamequiz.data.request.StoreQuestion
import com.kontrakanprojects.appgamequiz.data.room.MyDatabase

class GameActivity : AppCompatActivity() {

    private lateinit var gameView: GameView
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val point = Point()
        windowManager.defaultDisplay.getSize(point)

//        var question = Question(
//            "Bagian tumbuhan yang fungsinya menyangga tumbuhan dan tempat tumbuhnya daun, buah dan bunga.",
//            1
//        )
//        var question2 = Question(
//            "Tumbuhan yang dimanfaatkan untuk diambil kayu yang digunakan untuk perabot",
//            4
//        )
        viewModel = GameViewModel(QuestionRepository(MyDatabase.getDatabase(this)))
        createData()
        var questions = ArrayList<Question>()
//        viewModel.getQuestions().observe(this) {
//            //mapping result
//
//        }
        val it = viewModel.getQuestions()
        Log.d("GameActivity",it.toString())
        for (result in it) {
            val entityQ = result.question
            var resultOptions = ArrayList<Option>()
            for (opt in result.options) {
                val option = Option(
                    opt.image,
                    opt.numberSequence
                )
                resultOptions.add(option)
            }
            val question = Question(
                entityQ.text,
                entityQ.answerKey,
                resultOptions
            )
            questions.add(question)
        }
        gameView = GameView(this, point.x, point.y,questions)

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
//        var questions = ArrayList<Question>()
//        questions.add(question)
//        questions.add(question2)

    }

    private fun createData() {
        val question = QuestionEntity(
            1,
            "Temasuk bagian tumbuhan dan terletak dibawah tanah",
            3
        )

        val question2 = QuestionEntity(
            2,
            "Bagian tumbuhan yang fungsinya menyangga tumbuhan dan tempat tumbuhnya daun, buah dan bunga.",
            2
        )
        val option1 = OptionEntity(
            1,
            BitmapFactory.decodeResource(resources, R.drawable.fly1),
            1,
            1
        )
        val option2 = OptionEntity(
            2,
            BitmapFactory.decodeResource(resources, R.drawable.akar),
            2,
            1
        )

        val option3 = OptionEntity(
            3,
            BitmapFactory.decodeResource(resources, R.drawable.ufo),
            1,
            2
        )
        val option4 = OptionEntity(
            4,
            BitmapFactory.decodeResource(resources, R.drawable.akar),
            2,
            2
        )
        viewModel.insertQuestion(question)
        viewModel.insertQuestion(question2)
        viewModel.insertOptions(option1,option2,option3,option4)

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