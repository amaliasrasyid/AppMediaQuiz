package com.kontrakanprojects.appgamequiz.view.game

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Point
import android.media.AudioAttributes
import android.media.MediaPlayer
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
    private lateinit var audioRaw: AssetFileDescriptor
    private var mediaPlayer = MediaPlayer()

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

        //prepare media player for bacground game music
        mediaPlayer.isLooping = true
        audioRaw = resources.openRawResourceFd(R.raw.game_music_n11db)
        prepareMediaPlayer()


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

    /**
     *  load music resource on media player
     */
    private fun prepareMediaPlayer() {
        val attribute = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        try{
            mediaPlayer.setAudioAttributes(attribute)
            mediaPlayer.setDataSource(audioRaw.fileDescriptor,audioRaw.startOffset,audioRaw.length)
            mediaPlayer.prepare()
        }catch (e: Exception){
            Log.e(TAG,"Prepare Media Player: ${e.message}")
        }
        mediaPlayer.setOnPreparedListener { mediaPlayer.start() }
    }

    override fun onResume() {
        super.onResume()
        gameView.resume()
    }

    override fun onPause() {
        super.onPause()
        gameView.pause()
    }

    override fun onStart() {
        super.onStart()
        mediaPlayer.start()
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}