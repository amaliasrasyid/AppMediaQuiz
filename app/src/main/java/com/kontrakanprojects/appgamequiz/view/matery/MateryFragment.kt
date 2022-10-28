package com.kontrakanprojects.appgamequiz.view.matery

import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.databinding.FragmentMateryBinding
import com.kontrakanprojects.appgamequiz.util.MaterialType

class MateryFragment : Fragment(), View.OnTouchListener {
    private lateinit var binding: FragmentMateryBinding
    private lateinit var mediaPlayer: MediaPlayer
    private  var audioRaw: AssetFileDescriptor? = null
    private var latestPositionMusic: Int? = null

    private val TAG = MateryFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentMateryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaPlayer = MediaPlayer()

        //set listener
        with(binding){
            imgPlantMatery.setOnTouchListener(this@MateryFragment)
            imgAnimalMatery.setOnTouchListener(this@MateryFragment)
            imgEnvMatery.setOnTouchListener(this@MateryFragment)
            tvPlantMatery.setOnTouchListener(this@MateryFragment)
            tvAnimalMatery.setOnTouchListener(this@MateryFragment)
            tvEnvMatery.setOnTouchListener(this@MateryFragment)
            btnExit.setOnClickListener { findNavController().navigateUp() }
        }
    }



    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        val action = event?.action
        var type: MaterialType =  MaterialType.PLANT

        if(view is ImageView){
            if(audioRaw != null) mediaPlayer.reset()

            with(binding){
                when(view){
                    imgPlantMatery ->  {
                        audioRaw = requireContext().resources.openRawResourceFd(R.raw.plant_material_sound)
                    }
                    imgAnimalMatery -> {
                        audioRaw = requireContext().resources.openRawResourceFd(R.raw.animal_material_sound)
                        type = MaterialType.ANIMAL
                    }
                    imgEnvMatery -> {
                        audioRaw = requireContext().resources.openRawResourceFd(R.raw.natural_env_matery_sound)
                        type = MaterialType.ENVIRONMENT
                    }
                }
            }

            when(action){
                MotionEvent.ACTION_UP -> { //saat touch dilepas
                    prepareMediaPlayer()
                }
            }
        }else{
            with(binding){
                when(view){
                    tvAnimalMatery -> type = MaterialType.ANIMAL
                    tvEnvMatery -> type = MaterialType.ENVIRONMENT
                }
            }
            when(action){
                MotionEvent.ACTION_UP -> moveToMaterialContent(type)
                MotionEvent.ACTION_DOWN -> {
                    var textview = view as TextView
                    textview.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryColor))
                }
                MotionEvent.ACTION_MOVE -> {
                    var textview = view as TextView
                    textview.setTextColor(ContextCompat.getColor(requireContext(),R.color.primaryTextColor))
                }
            }
        }

        return true
    }

    private fun moveToMaterialContent(type: MaterialType) {
        val toMaterialContent = MateryFragmentDirections.actionMateryFragmentToMaterialContentFragment(type)
        findNavController().navigate(toMaterialContent)
    }

    //load music resource on media player
    fun prepareMediaPlayer(){
        val attribute = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()

        try{
            mediaPlayer.setAudioAttributes(attribute)
            audioRaw?.let {
                mediaPlayer.setDataSource(it.fileDescriptor,
                    it.startOffset,it.length)
            }
            mediaPlayer.prepare()
        }catch (e: Exception){
            Log.e(TAG,"Prepare Media Player: ${e.message}")
        }

        mediaPlayer.setOnPreparedListener{
            mediaPlayer.start()
        }
    }

    override fun onStart() {
        super.onStart()
        if(latestPositionMusic != null){
            mediaPlayer.seekTo(latestPositionMusic!!)
            mediaPlayer.start()
        }
    }

    override fun onStop() {
        super.onStop()
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
            latestPositionMusic = mediaPlayer.currentPosition
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.release()
    }
}