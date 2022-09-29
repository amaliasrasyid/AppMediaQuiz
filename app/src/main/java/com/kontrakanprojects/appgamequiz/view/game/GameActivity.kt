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
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.entity.OptionEntity
import com.kontrakanprojects.appgamequiz.data.entity.QuestionEntity
import com.kontrakanprojects.appgamequiz.data.model.Option
import com.kontrakanprojects.appgamequiz.data.model.Question
import com.kontrakanprojects.appgamequiz.data.repository.QuestionRepository
import com.kontrakanprojects.appgamequiz.data.request.StoreOption
import com.kontrakanprojects.appgamequiz.data.request.StoreQuestion
import com.kontrakanprojects.appgamequiz.data.room.MyDatabase
import com.kontrakanprojects.appgamequiz.util.Move

class GameActivity : AppCompatActivity() {

    private lateinit var gameView: GameView
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val point = Point()
        windowManager.defaultDisplay.getSize(point)

        viewModel = GameViewModel(QuestionRepository(MyDatabase.getDatabase(this)))
        createData()
        var questions = ArrayList<Question>()
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
        gameView = GameView(this,this, point.x, point.y,questions)

        setContentView(gameView)
        val move = Move(this@GameActivity)
        move.moveToResult()

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