package com.kontrakanprojects.appgamequiz.view.quiz

import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.request.StoreStudentScoreRequest
import com.kontrakanprojects.appgamequiz.data.session.UserPreference
import com.kontrakanprojects.appgamequiz.databinding.ActivityEndQuizBinding
import com.kontrakanprojects.appgamequiz.util.Status
import com.kontrakanprojects.appgamequiz.view.MainActivity

class EndQuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEndQuizBinding
    private val viewModel: QuizViewModel by viewModels()
    private var score = 0
    private lateinit var failMp: MediaPlayer
    private lateinit var successMp: MediaPlayer

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

        successMp = MediaPlayer.create(this@EndQuizActivity,R.raw.success)
        failMp = MediaPlayer.create(this@EndQuizActivity,R.raw.fail)

//        listener button
        binding.btnOk.setOnClickListener{
            saveScore()
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
                    failMp.start()
                }else{
                    //satisfied result
                    tvBannerText.text = "Memuaskan!"
                    imgStar.setImageDrawable(getDrawable(R.drawable.success_star))
                    successMp.start()
                }
                tvScoreResult.text = score.toString()
            }
        }
    }

    private fun saveScore() {
        val request = getRequest()
        observableViewModel(request)
    }

    private fun observableViewModel(request: StoreStudentScoreRequest) {
        viewModel.storeStudentScore(request).observe(this){ response ->
            when(response.status){
                Status.LOADING -> loader(true)
                Status.SUCCESS -> {
                    loader(false)
                    moveToMainActivity()
                }
                Status.ERROR -> {
                    loader(false)
                }
            }
        }
    }

    private fun moveToMainActivity() {
        startActivity(Intent(this@EndQuizActivity,MainActivity::class.java))
        finish()
    }

    private fun getRequest(): StoreStudentScoreRequest {
        val id = 0
        val studentId = UserPreference(this@EndQuizActivity).getUser().id
        val nilai = score
        return StoreStudentScoreRequest(id,studentId!!,nilai)
    }

    private fun calculateScore(correctAnswer: Int, sizeQuizQ: Int,): Int {
        val maxQ = sizeQuizQ
        val point = 100/maxQ

        return point * correctAnswer as (Int)
    }

    private fun loader(state: Boolean) {
        with(binding) {
            if (state) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }
}