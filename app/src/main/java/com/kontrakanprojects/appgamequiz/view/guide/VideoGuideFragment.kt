package com.kontrakanprojects.appgamequiz.view.guide

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.navigation.fragment.findNavController
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.databinding.FragmentVideoGuideBinding
import timber.log.Timber

class VideoGuideFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentVideoGuideBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoGuideBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnExit.setOnClickListener(this@VideoGuideFragment)


            //PREPARE VIDEO
            val videoPath = "android.resource://"+activity?.packageName+"/"+R.raw.screen_record_playing_game
            vvScreenRecordGame.setVideoURI(Uri.parse(videoPath))
            Timber.d("video path: "+videoPath)

            //ADD MEDIA CONTROLLER LIKE PLAY,PAUSE,RESUME
            val mediaController = MediaController(context)
            mediaController.setAnchorView(vvScreenRecordGame)
            vvScreenRecordGame.setMediaController(mediaController)
            vvScreenRecordGame.start() //AUTO START VIDEO
        }
    }

    override fun onClick(view: View?) {
        with(binding){
            when(view){
                btnExit -> findNavController().navigateUp()
                else -> {}
            }
        }
    }
}