package com.kontrakanprojects.appgamequiz.view.matery

import android.content.Context
import android.media.MediaPlayer
import android.os.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.databinding.FragmentIntermezzoQuizBinding
import com.kontrakanprojects.appgamequiz.util.MaterialType

class IntermezzoQuizFragment : Fragment(), CompoundButton.OnCheckedChangeListener {
    private lateinit var binding: FragmentIntermezzoQuizBinding
    private lateinit var rightResult: ArrayList<ImageView>
    private lateinit var wrongResult: ArrayList<ImageView>
    private lateinit var vibrator: Vibrator
    private lateinit var wrongMp: MediaPlayer
    private lateinit var rightMp: MediaPlayer
    private val DELAY_TIME = 1500L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIntermezzoQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rightResult = ArrayList()
        wrongResult = ArrayList()
        rightMp = MediaPlayer.create(requireContext(),R.raw.correct)
        wrongMp = MediaPlayer.create(requireContext(),R.raw.wrong)


        vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        with(binding) {
            //set listener
            cbHealthyPlant.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbHealthyPlant3.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbUnhealthyPlant.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbUnhealthyPlant2.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbUnhealthyPlant4.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbUnhealthyPlant5.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbUnhealthyPlant6.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbUnhealthyChili.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbHealthyGoat.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbHealthyHorse.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbHealthyCow2.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbHealthyChicken.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbUnkemptCat.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbUnkemptCow.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbLushScenery.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            cbDeathPuppy.setOnCheckedChangeListener(this@IntermezzoQuizFragment)
            btnNext.setOnClickListener{
                //cek apa semua jawaban sudah terceklis?
                with(binding){
                    if(cbHealthyPlant.isChecked && cbHealthyGoat.isChecked && cbHealthyHorse.isChecked && cbHealthyChicken.isChecked  && cbHealthyPlant3.isChecked && cbLushScenery.isChecked && cbHealthyCow2.isChecked) {
                        val toEnvMaterial =
                            IntermezzoQuizFragmentDirections.actionIntermezzoQuizFragmentToMaterialContentFragment(
                                MaterialType.ENVIRONMENT
                            )
                        findNavController().navigate(toEnvMaterial)
                    }else{
                        vibrate()
                        Toast.makeText(requireContext(),"Pastikan Semua jawaban yang benar telah di centang",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        if (buttonView.isChecked) {
            val (checkedImage, isRight) = verifyCheckedAnswer(buttonView)
            if (isRight) {
                if(rightMp.isPlaying) rightMp.pause()
                rightMp.start()
            } else {
                if(wrongMp.isPlaying) wrongMp.pause()
                wrongMp.start()
                vibrate()
                buttonView.isChecked = false
            }
        }
    }

    private fun verifyCheckedAnswer(buttonView: CompoundButton): Pair<ImageView, Boolean> {
        var imgResult: ImageView
        var isRight = false
        with(binding) {
            when (buttonView) {
                cbHealthyPlant -> {
                    isRight = true
                    imgResult = imHealthyPlant
                }
                cbHealthyGoat -> {
                    isRight = true
                    imgResult = imHealthyGoat
                }
                cbUnhealthyPlant -> {
                    isRight = false
                    imgResult = imUnhealthyPlant
                }
                cbHealthyHorse -> {
                    isRight = true
                    imgResult = imHealthyHorse
                }
                cbUnhealthyPlant2 -> {
                    isRight = false
                    imgResult = imUnhealthyPlant2
                }
                cbUnkemptCat -> {
                    isRight = false
                    imgResult = imUnkemptCat
                }
                cbHealthyChicken -> {
                    isRight = true
                    imgResult = imHealthyChicken
                }
                cbHealthyPlant3 -> {
                    isRight = true
                    imgResult = imHealthyPlant3
                }
                cbUnkemptCow -> {
                    isRight = false
                    imgResult = imUnkemptCow
                }
                cbUnhealthyPlant4 -> {
                    isRight = false
                    imgResult = imUnhealthyPlant4
                }
                cbUnhealthyPlant5 -> {
                    isRight = false
                    imgResult = imUnhealthyPlant5
                }
                cbLushScenery -> {
                    isRight = true
                    imgResult = imLushScenery
                }
                cbUnhealthyPlant6 -> {
                    isRight = false
                    imgResult = imUnhealthyPlant6
                }
                cbUnhealthyChili -> {
                    isRight = false
                    imgResult = imUnhealthyChili
                }
                cbHealthyCow2 -> {
                    isRight = true
                    imgResult = imHealthyCow2
                }
                else -> {
                    isRight = false
                    imgResult = imStrayDog
                }
            }
        }
        return Pair(imgResult, isRight)
    }

    private fun vibrate(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }
}