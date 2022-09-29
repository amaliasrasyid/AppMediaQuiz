package com.kontrakanprojects.appgamequiz.view.competency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.databinding.FragmentCompetencyBinding


class CompetencyFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentCompetencyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompetencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            btnExit.setOnClickListener(this@CompetencyFragment)
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