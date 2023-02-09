package com.kontrakanprojects.appgamequiz.view.matery

import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.databinding.FragmentMaterialContentBinding
import com.kontrakanprojects.appgamequiz.databinding.FragmentQuizBinding
import com.kontrakanprojects.appgamequiz.util.MaterialType
import com.kontrakanprojects.appgamequiz.view.quiz.QuizFragment
import timber.log.Timber

class MaterialContentFragment : Fragment(), View.OnTouchListener {
    private lateinit var binding: FragmentMaterialContentBinding
    private val args: MaterialContentFragmentArgs by navArgs()
    private var layoutType: MaterialType = MaterialType.PLANT
    private lateinit var mediaPlayer: MediaPlayer
    private var audioRaw: AssetFileDescriptor? = null
    private var latestPositionAudio: Int? = null

    private val TAG = MaterialContentFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMaterialContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaPlayer = MediaPlayer()
        with(binding) {
            btnExit.setOnClickListener { findNavController().navigateUp() }
            when (args.mType) {
                MaterialType.ANIMAL -> {
                    //hide others and show animals material layout
                    val layout = layoutAnimal.root
                    val otherLayout1 = layoutPlant.root
                    val otherLayout2 = layoutEnvironment.root
                    layout.visibility = View.VISIBLE
                    otherLayout1.visibility = View.GONE
                    otherLayout2.visibility = View.GONE

                    prepareOnClickImage()
                }
                MaterialType.ENVIRONMENT -> {
                    val layout = layoutEnvironment.root
                    val otherLayout1 = layoutPlant.root
                    val otherLayout2 = layoutAnimal.root
                    layout.visibility = View.VISIBLE
                    otherLayout1.visibility = View.GONE
                    otherLayout2.visibility = View.GONE
                }
                else -> {
                    // DO NOTHING;DEFAULT IS THE PLANT LAYOUT
                }
            }
        }

    }

    private fun prepareOnClickImage() {
        with(binding.layoutAnimal) {
            imgTiger.setOnTouchListener(this@MaterialContentFragment)
            imgOrangutan.setOnTouchListener(this@MaterialContentFragment)
            imgElephant.setOnTouchListener(this@MaterialContentFragment)
            imgCamel.setOnTouchListener(this@MaterialContentFragment)
            imgLizard.setOnTouchListener(this@MaterialContentFragment)
            imgSnake.setOnTouchListener(this@MaterialContentFragment)
            imgPolarBear.setOnTouchListener(this@MaterialContentFragment)
            imgPenguin.setOnTouchListener(this@MaterialContentFragment)
            imgArcticFox.setOnTouchListener(this@MaterialContentFragment)
            imgCat.setOnTouchListener(this@MaterialContentFragment)
            imgDog.setOnTouchListener(this@MaterialContentFragment)
            imgParrot.setOnTouchListener(this@MaterialContentFragment)
            imgChicken.setOnTouchListener(this@MaterialContentFragment)
            imgCow.setOnTouchListener(this@MaterialContentFragment)
            imgRabbit.setOnTouchListener(this@MaterialContentFragment)
        }
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        val action = event?.action

        //PREPARE AUDIO
        with(binding.layoutAnimal) {
            when (view) {
                imgTiger -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.harimau)
                imgOrangutan -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.orang_utan)
                imgElephant -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.gajah)
                imgCamel -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.unta)
                imgLizard -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.biawak_pasir)
                imgSnake -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.ular_padang_pasir)
                imgPolarBear -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.beruang_kutub)
                imgPenguin -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.burung_pinguin)
                imgArcticFox -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.rubah_kutub)
                imgCat -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.kucing)
                imgDog -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.anjing)
                imgParrot -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.burung_nuri)
                imgChicken -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.ayam)
                imgCow -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.sapi)
                imgRabbit -> audioRaw = requireContext().resources.openRawResourceFd(R.raw.kelinci)
            }
        }

        //DEFINE TOUCH ACTION
        when(action){
            MotionEvent.ACTION_DOWN,MotionEvent.ACTION_UP -> { //Saat Img di Touch atau Touch-nya dilepas
                Timber.d("audio raw: ${audioRaw}")
                Timber.d("view touched: ${view}")
                prepareMediaPlayer()
            }
        }
        return true
    }

    private fun prepareMediaPlayer() {
        val attribute = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()

        try{
            mediaPlayer.setAudioAttributes(attribute)
            audioRaw?.let{
                mediaPlayer.setDataSource(it.fileDescriptor,it.startOffset,it.length)
            }
            mediaPlayer.prepare()
        }catch (e: Exception){
            Timber.e("Prepare Media Player: ${e.message}")
        }
        mediaPlayer.setOnPreparedListener{ mediaPlayer.start() }
    }

    override fun onStart() {
        super.onStart()
        if(latestPositionAudio != null){
            mediaPlayer.seekTo(latestPositionAudio!!) //resume audio
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

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.release()
    }
}