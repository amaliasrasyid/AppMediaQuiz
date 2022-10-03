package com.kontrakanprojects.appgamequiz.view.game

import android.content.Intent
import android.graphics.Color
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

        //listener button
        binding.btnOk.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            startActivity(intent)
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

                        GamePreference(this@EndGameActivity).setGameState(true)
                    }
                    TYPE_GAME_OVER -> {
                        imgStar.setImageDrawable(getDrawable(R.drawable.fail_star))
                        tvScoreResult.text = "0"
                        tvScoreResult.setTextColor(Color.RED)
                        tvBannerText.text = getString(R.string.text_failing)
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