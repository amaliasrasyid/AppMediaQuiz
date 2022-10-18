package com.kontrakanprojects.appgamequiz.view.student_score

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kontrakanprojects.appgamequiz.data.source.remote.StudentRemoteResource
import com.kontrakanprojects.appgamequiz.databinding.FragmentQuizBinding
import com.kontrakanprojects.appgamequiz.databinding.FragmentStudentScoresBinding
import com.kontrakanprojects.appgamequiz.util.Status
import com.kontrakanprojects.appgamequiz.util.mySnackBar

class StudentScoresFragment : Fragment() {
    private lateinit var binding: FragmentStudentScoresBinding
    private lateinit var stdScoreAdapter: StudentScoreAdapter
    private val viewmodel: StudentScoreViewModel by viewModels()

    private val TAG = StudentScoresFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentScoresBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            //adapter
            stdScoreAdapter = StudentScoreAdapter()
            with(rvStudentScore){
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = stdScoreAdapter
            }

            btnExit.setOnClickListener{
                findNavController().navigateUp()
            }
        }

        observeStudentScores()
    }

    private fun observeStudentScores() {
        viewmodel.getAll().observe(viewLifecycleOwner){result ->
            when(result.status){
                Status.LOADING -> loader(true)
                Status.SUCCESS -> {
                    loader(false)
                    if(result.data?.isEmpty() ?: false){
                        //TODO show empty layout
                    }
                    stdScoreAdapter.setData(result.data)
                    Log.d(TAG,"success response")
                }
                Status.ERROR -> {
                    loader(false)
                    view?.mySnackBar(result.message.toString())
                }
            }
        }
    }
    private fun loader(state: Boolean) {
        with(binding) {
            if (state) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }
}