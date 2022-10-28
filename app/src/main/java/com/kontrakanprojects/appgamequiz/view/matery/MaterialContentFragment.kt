package com.kontrakanprojects.appgamequiz.view.matery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
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

class MaterialContentFragment : Fragment() {
    private lateinit var binding: FragmentMaterialContentBinding
    private val args: MaterialContentFragmentArgs by navArgs()

    private val TAG = MaterialContentFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMaterialContentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            btnExit.setOnClickListener{ findNavController().navigateUp() }
            when(args.mType){
                MaterialType.ANIMAL -> {
                    //hide others and show animals material layout
                   val layout = layoutAnimal.root
                    val otherLayout1 = layoutPlant.root
                    val otherLayout2 = layoutEnvironment.root
                    layout.visibility = View.VISIBLE
                    otherLayout1.visibility = View.GONE
                    otherLayout2.visibility = View.GONE
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

                }
            }
        }

    }
}