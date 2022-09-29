package com.kontrakanprojects.appgamequiz.view.guide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.databinding.FragmentGuideBinding
import com.kontrakanprojects.appgamequiz.databinding.FragmentMainBinding


/**
 * A simple [Fragment] subclass.
 * Use the [GuideFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GuideFragment : Fragment(), View.OnClickListener {
   private lateinit var binding: FragmentGuideBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGuideBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            btnExit.setOnClickListener(this@GuideFragment)
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