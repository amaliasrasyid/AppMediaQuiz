package com.kontrakanprojects.appgamequiz.view.game

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.session.GamePreference
import com.kontrakanprojects.appgamequiz.databinding.ActivityEndGameBinding
import com.kontrakanprojects.appgamequiz.view.MainActivity

class EndGameActivity :AppCompatActivity() {
    private lateinit var binding: ActivityEndGameBinding
    private lateinit var failMp: MediaPlayer
    private lateinit var successMp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEndGameBinding.inflate(layoutInflater)
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
        successMp = MediaPlayer.create(this@EndGameActivity,R.raw.success)
        failMp = MediaPlayer.create(this@EndGameActivity,R.raw.fail)

        //listener button
        binding.btnOk.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val intentExtra = intent.extras
        if(intentExtra != null){
            val result = intentExtra.getInt(TYPE_RESULT)
            with(binding){
                when(result){
                    TYPE_GAME_SUCCESS -> {
                        imgStar.setImageDrawable(getDrawable(R.drawable.star_success))
                        tvScoreResult.text = "100"
                        tvBannerText.text = getString(R.string.text_winning)
                        successMp.start()
                        GamePreference(this@EndGameActivity).setGameState(true)
                    }
                    TYPE_GAME_OVER -> {
                        imgStar.setImageDrawable(getDrawable(R.drawable.fail_star))
                        tvScoreResult.text = "0"
                        tvScoreResult.setTextColor(Color.RED)
                        tvBannerText.text = getString(R.string.text_failing)
                        failMp.start()
                    }
                }
            }
        }
    }

    companion object {
        const val TYPE_RESULT = "type_result"
        const val TYPE_GAME_SUCCESS = 1
        const val TYPE_GAME_OVER = 2
    }

}