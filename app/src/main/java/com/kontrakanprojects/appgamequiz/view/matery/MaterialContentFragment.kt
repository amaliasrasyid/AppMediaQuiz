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
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.databinding.FragmentMaterialContentBinding
import com.kontrakanprojects.appgamequiz.util.MaterialType
import com.kontrakanprojects.appgamequiz.view.MainActivity
import timber.log.Timber
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE

class MaterialContentFragment : Fragment(), View.OnTouchListener {
    private lateinit var binding: FragmentMaterialContentBinding
    private val args: MaterialContentFragmentArgs by navArgs()
    private var layoutType: MaterialType = MaterialType.PLANT
    private lateinit var mediaPlayer: MediaPlayer
    private var audioRaw: AssetFileDescriptor? = null
    private lateinit var audioRawMain: AssetFileDescriptor
    private var latestPositionAudio: Int? = null

    private var indexMatery = 1
    private var isPrev = false

    private val TAG = MaterialContentFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragmen
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

                    prepareOnTouchImage()
                    audioRawMain = requireContext().resources.openRawResourceFd(R.raw.audio_materi_hewan)
                }
                MaterialType.ENVIRONMENT -> {
                    val layout = layoutEnvironment.root
                    val otherLayout1 = layoutPlant.root
                    val otherLayout2 = layoutAnimal.root
                    layout.visibility = View.VISIBLE
                    otherLayout1.visibility = View.GONE
                    otherLayout2.visibility = View.GONE

                    audioRawMain = requireContext().resources.openRawResourceFd(R.raw.audio_materi_lingkungan)
                }
                else -> {
                    //DEFAULT IS THE PLANT LAYOUT
                    with(layoutPlant){
                        btnNext.setOnClickListener{
                            isPrev = false
                            hideCurrentMatery()
                            indexMatery++
                            Timber.d("index Matery: ${indexMatery}")

                            showNextMatery()
                            Timber.d("Clicked Next")
                            //Show or Hide Button Prev-Next
                            if(indexMatery == 1){
                                showPrevNextButtons(false,true)
                            }else if(indexMatery < 9){
                                showPrevNextButtons(true,true)
                            }else if(indexMatery >= 9 ){
                                showPrevNextButtons(true,false)
                            }
                        }
                        btnPrev.setOnClickListener {
                            isPrev = true
                            indexMatery--
                            hideCurrentMatery()
                            Timber.d("index Matery: ${indexMatery}")

                            showNextMatery()
                            //Show or Hide Button Prev-Next
                            if(indexMatery == 1){
                                showPrevNextButtons(false,true)
                            }else if(indexMatery < 9){
                                showPrevNextButtons()
                            }else if(indexMatery >= 9 ){
                                showPrevNextButtons(true,false)
                            }
                            Timber.d("Clicked Prev")
                        }
                    }
                    audioRawMain = requireContext().resources.openRawResourceFd(R.raw.audio_materi_tanaman)
                }
            }
        }

        val animalLayout = binding.layoutAnimal.root
        Timber.d("Status Type Layout View: ${animalLayout.visibility == View.GONE}")


        //change source & prepare matery music
        val act = activity
        if(act is MainActivity){
            act.changeAudioRes(audioRawMain)
        }
    }

    private fun showNextMatery() {
        with(binding.layoutPlant){
           when(indexMatery){
               1 -> layoutFirst.root.visibility= View.VISIBLE
               2 -> layoutAkar.root.visibility= View.VISIBLE
               3 -> layoutBatang.root.visibility= View.VISIBLE
               4 -> layoutBunga.root.visibility= View.VISIBLE
               5 -> layoutDaun.root.visibility= View.VISIBLE
               6 -> layoutBiji.root.visibility= View.VISIBLE
               7 -> layoutBuah.root.visibility= View.VISIBLE
               8 -> layoutPengamatan.root.visibility= View.VISIBLE
               9 -> layoutRagamTumbuhan.root.visibility= View.VISIBLE
           }
        }
    }

    private fun hideCurrentMatery() {
        with(binding.layoutPlant){
            when(indexMatery){
                1 -> {
                    if(isPrev){
                        layoutAkar.root.visibility = View.GONE
                    }else{
                        layoutFirst.root.visibility = View.GONE
                    }
                }
                2 -> {
                    if(isPrev){
                        layoutBatang.root.visibility = View.GONE
                    }else{
                        layoutAkar.root.visibility = View.GONE
                    }
                }
                3 -> {
                    if(isPrev){
                        layoutBunga.root.visibility = View.GONE
                    }else{
                        layoutBatang.root.visibility = View.GONE
                    }
                }
                4 -> {
                    if(isPrev){
                        layoutDaun.root.visibility = View.GONE
                    }else{
                        layoutBunga.root.visibility = View.GONE
                    }
                }
                5 -> {
                    if(isPrev){
                        layoutBiji.root.visibility= View.GONE
                    }else{
                        layoutDaun.root.visibility = View.GONE
                    }

                }
                6 -> {
                    if(isPrev){
                        layoutBuah.root.visibility = View.GONE
                    }else{
                        layoutBiji.root.visibility = View.GONE
                    }
                }
                7 -> {
                    if(isPrev){
                        layoutPengamatan.root.visibility = View.GONE
                    }else{
                        layoutBuah.root.visibility = View.GONE
                    }
                }
                8 -> {
                    if(isPrev){
                        layoutRagamTumbuhan.root.visibility = View.GONE
                    }else{
                        layoutPengamatan.root.visibility = View.GONE
                    }
                }
                9 -> layoutRagamTumbuhan.root.visibility = View.GONE
            }
        }

    }

    protected fun showPrevNextButtons(statusPrev: Boolean = TRUE, statusNext: Boolean = TRUE){
        with(binding.layoutPlant){
            when{
                statusPrev == FALSE && statusNext == TRUE -> {
                    btnPrev.visibility= View.INVISIBLE
                    btnNext.visibility= View.VISIBLE
                }
                statusPrev == TRUE && statusNext == FALSE -> {
                    btnPrev.visibility= View.VISIBLE
                    btnNext.visibility= View.INVISIBLE
                }
                else -> {
                    btnPrev.visibility= View.VISIBLE
                    btnNext.visibility= View.VISIBLE
                }
            }
        }
    }

    private fun prepareOnTouchImage() {
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
        with(binding.layoutPlant){
//            tvSubAkar.setOnTouchListener(this@MaterialContentFragment)
//            tvSubDaun.setOnTouchListener(this@MaterialContentFragment)
//            tvSubBatang.setOnTouchListener(this@MaterialContentFragment)
//            tvSubBuah.setOnTouchListener(this@MaterialContentFragment)
//            tvSubBiji.setOnTouchListener(this@MaterialContentFragment)
//            tvSubBunga.setOnTouchListener(this@MaterialContentFragment)
        }
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        val action = event?.action

        //PREPARE AUDIO n RESET IF AUDIO HAS BEEN SET
        if(audioRaw != null) mediaPlayer.reset()
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
        audioRawMain = resources.openRawResourceFd(R.raw.quiz_music_n11db)
        val act = activity
        if(act is MainActivity){
            act.changeAudioRes(audioRawMain)
        }
        mediaPlayer.release()
    }

}