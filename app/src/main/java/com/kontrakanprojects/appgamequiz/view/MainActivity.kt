package com.kontrakanprojects.appgamequiz.view

import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.session.GamePreference

class MainActivity : AppCompatActivity() {

    private  val TAG = MainActivity::class.java.simpleName
    private lateinit var audioRaw: AssetFileDescriptor
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //prepare media player for playing app music
        mediaPlayer = MediaPlayer()
        mediaPlayer.isLooping = true
        audioRaw = resources.openRawResourceFd(R.raw.quiz_music)
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

    override fun onStart() {
        super.onStart()
        if(mediaPlayer != null) mediaPlayer.start()
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