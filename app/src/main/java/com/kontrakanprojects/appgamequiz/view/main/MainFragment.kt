package com.kontrakanprojects.appgamequiz.view.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.databinding.FragmentMainBinding
import com.kontrakanprojects.appgamequiz.view.game.GameActivity

class MainFragment : Fragment(),View.OnClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnGame.setOnClickListener(this@MainFragment)
            btnKiKd.setOnClickListener(this@MainFragment)
            btnPetunjuk.setOnClickListener(this@MainFragment)
            btnMateri.setOnClickListener(this@MainFragment)
            btnQuiz.setOnClickListener(this@MainFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(view: View?) {
        with(binding){
            when(view){
                btnGame -> moveToGame()
                btnKiKd -> moveToKiKd()
                btnPetunjuk -> moveToGuide()
            }
        }

    }

    private fun moveToGuide() {
        findNavController().navigate(R.id.action_mainFragment_to_guideFragment)
    }


    private fun moveToKiKd() {
        findNavController().navigate(R.id.action_mainFragment_to_competencyFragment)
    }

    private fun moveToGame() {
        findNavController().navigate(R.id.action_mainFragment_to_startGameFragment)
    }
}