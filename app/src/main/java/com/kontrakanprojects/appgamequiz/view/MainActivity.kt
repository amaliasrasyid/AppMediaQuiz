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

class MainActivity : AppCompatActivity() {

    private  val TAG = MainActivity::class.java.simpleName
    private lateinit var audioRaw: AssetFileDescriptor
    lateinit var mediaPlayer: MediaPlayer
    private var latestPositionAudio: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //prepare media player for playing app music
        mediaPlayer = MediaPlayer()
        mediaPlayer.isLooping = true
        audioRaw = resources.openRawResourceFd(R.raw.quiz_music_n11db)
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

    public fun changeAudioRes(audioRaw: AssetFileDescriptor){
        mediaPlayer.reset()
        this.audioRaw = audioRaw
        prepareMediaPlayer()
    }

    override fun onStart() {
        super.onStart()
        if(latestPositionAudio != null){
            mediaPlayer.seekTo(latestPositionAudio!!)
            mediaPlayer.start()
        }
    }

    override fun onStop() {
        super.onStop()
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
            latestPositionAudio = mediaPlayer.currentPosition
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

}