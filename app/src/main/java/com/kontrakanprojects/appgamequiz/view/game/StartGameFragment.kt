package com.kontrakanprojects.appgamequiz.view.game

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.databinding.FragmentStartGameBinding

class StartGameFragment : Fragment(),View.OnClickListener {

    private lateinit var binding: FragmentStartGameBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            btnStartGame.setOnClickListener(this@StartGameFragment)
            btnExit.setOnClickListener(this@StartGameFragment)
        }
    }

    override fun onClick(view: View?) {
        with(binding){
            when(view){
                btnStartGame -> startActivity(Intent(requireContext(),GameActivity::class.java))
                btnExit -> findNavController().navigateUp()
                else -> {}
            }
        }

    }
}