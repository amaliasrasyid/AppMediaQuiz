package com.kontrakanprojects.appgamequiz.view.quiz

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.databinding.ActivityEndQuizBinding
import com.kontrakanprojects.appgamequiz.view.MainActivity

class EndQuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEndQuizBinding
    private var score = 0

    companion object {
        const val COUNT_CORRECT_ANSWER = "count correct answer"
        const val QUIZ_Q_SIZE = "quiz question size"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEndQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

//        listener button
        binding.btnOk.setOnClickListener{
            saveScore(score)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val intentExtra = intent.extras
        if(intentExtra != null){
            val correctAnswer = intentExtra.getInt(COUNT_CORRECT_ANSWER)
            val sizeQuizQ = intentExtra.getInt(QUIZ_Q_SIZE)
            score = calculateScore(correctAnswer,sizeQuizQ)
            with(binding){
                if(score <= 60){
                    //not satisfied result
                    tvBannerText.text = "Kurang Memuaskan"
                    imgStar.setImageDrawable(getDrawable(R.drawable.fail_star))

                }else{
                    //satisfied result
                    tvBannerText.text = "Memuaskan!"
                    imgStar.setImageDrawable(getDrawable(R.drawable.success_star))
                }
                tvScoreResult.text = score.toString()
            }
        }
    }

    private fun saveScore(score: Int) {
//        val request = getRequest()
//        obs
    }

    private fun calculateScore(correctAnswer: Int, sizeQuizQ: Int,): Int {
        val maxQ = sizeQuizQ
        val point = 100/maxQ

        return point * correctAnswer as (Int)
    }
}