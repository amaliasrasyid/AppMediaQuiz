package com.kontrakanprojects.appgamequiz.view.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.session.GamePreference
import com.kontrakanprojects.appgamequiz.data.session.UserPreference
import com.kontrakanprojects.appgamequiz.databinding.FragmentMainBinding
import com.kontrakanprojects.appgamequiz.util.mySnackBar
import com.kontrakanprojects.appgamequiz.view.auth.AuthActivity
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
            btnProfile.setOnClickListener(this@MainFragment)
            btnKiKd.setOnClickListener(this@MainFragment)
            btnGame.setOnClickListener(this@MainFragment)
            btnPetunjuk.setOnClickListener(this@MainFragment)
            btnMateri.setOnClickListener(this@MainFragment)
            btnQuiz.setOnClickListener(this@MainFragment)
            btnExit.setOnClickListener(this@MainFragment)
        }
    }

    override fun onClick(view: View?) {
        with(binding){
            when(view){
                btnProfile -> moveToProfile()
                btnKiKd -> moveToKiKd()
                btnGame -> moveToGame()
                btnQuiz -> moveToQuiz()
                btnPetunjuk -> moveToGuide()
                btnExit -> logOut()
            }
        }

    }

    private fun moveToQuiz() {
        //check if game state is true (have finished the game)
        val isGameFinished = GamePreference(requireContext()).getGameState()
        if(isGameFinished){
            findNavController().navigate(R.id.action_mainFragment_to_quizFragment)
        }else{
            view?.mySnackBar("Selesaikan Game terlebih dahulu sebelum akses menu Quiz")
        }

    }

    private fun moveToProfile() {
        findNavController().navigate(R.id.action_mainFragment_to_userProfileFragment)
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

    private fun logOut() {
        UserPreference(requireContext()).logout()
        val intent = Intent(activity, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        activity?.finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}