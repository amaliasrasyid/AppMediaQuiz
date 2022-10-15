package com.kontrakanprojects.appgamequiz.view.quiz

import android.app.ActionBar.LayoutParams
import android.graphics.Bitmap
import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginBottom
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.model.Option
import com.kontrakanprojects.appgamequiz.data.model.Question
import com.kontrakanprojects.appgamequiz.databinding.FragmentQuizBinding
import com.kontrakanprojects.appgamequiz.databinding.FragmentRegisterBinding
import com.kontrakanprojects.appgamequiz.util.DataQuiz

class QuizFragment : Fragment(),View.OnClickListener {
    private lateinit var binding: FragmentQuizBinding
    private lateinit var listQuizQ: ArrayList<Question>
    private var indexQuizQ = 0
    private var currentAnswerKey = 0
    private var score = 0
    private var indexLevel = 0
    private val DELAY_TIME = 500L
    private val TAG = QuizFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * 1. SIAPKAN SOAL : OK
         * 2. PREPARE VIEW DG PASS SOAL KE VIEW : OK
         * 3. SIAPKAN LISTENER KLIK item options
         * 4. SIAPKAN LOGIKA JIKA BENAR/SALAH
         */
        with(binding){
            tvOption1.setOnClickListener(this@QuizFragment)
            tvOption2.setOnClickListener(this@QuizFragment)
            tvOption3.setOnClickListener(this@QuizFragment)
            tvOption4.setOnClickListener(this@QuizFragment)
            imgOption1.setOnClickListener(this@QuizFragment)
            imgOption2.setOnClickListener(this@QuizFragment)
            imgOption3.setOnClickListener(this@QuizFragment)
            imgOption4.setOnClickListener(this@QuizFragment)
        }
        listQuizQ = DataQuiz.getQuestion(resources)
        prepareQuiz()
    }

    private fun prepareQuiz() {
        setDefaultCondition()
        val currentQuizQ = listQuizQ.get(indexQuizQ)
        currentAnswerKey = currentQuizQ.answerKey

        with(binding){
            tvCompetency.text = currentQuizQ.competencyName
            if(currentQuizQ.images == null){
                tvQuizQuestion.text = currentQuizQ.text
            }else{
                llContainerImagesQuestions.visibility = View.VISIBLE
                tvPayAttention.visibility = View.VISIBLE
                val countImagesQ = currentQuizQ.images.size
                if(countImagesQ == 2){
                    imgQ1.setImageBitmap(currentQuizQ.images.get(0))
                    imgQ2.setImageBitmap(currentQuizQ.images.get(1))
                }else if(countImagesQ == 1){
                    imgQ1.setImageBitmap(currentQuizQ.images.get(0))
                }
            }
            //options
            val isImage = if(currentQuizQ.options.get(0).image is Bitmap) true else false
            if(isImage){
                prepareOptionsImage(currentQuizQ.options)
            }else{
                prepareOptionsText(currentQuizQ.options)
            }
        }
    }

    private fun setDefaultCondition() {
        with(binding){
            llContainerImagesQuestions.visibility = GONE
            tvPayAttention.visibility = GONE
            glOptions.visibility = GONE
            llTextOptions.visibility = GONE

            //clear images in question
            imgQ1.setImageBitmap(null)
            imgQ2.setImageBitmap(null)
        }
    }

    private fun prepareOptionsText(options: List<Option>) {
        with(binding){
            llTextOptions.visibility = View.VISIBLE
            for((index,option) in options.withIndex()){
                var textView = TextView(requireContext())
                when(index){
                    0 -> textView = tvOption1
                    1 -> textView = tvOption2
                    2 -> textView = tvOption3
                    3 -> textView = tvOption4
                }
                with(textView){
                    setText(option.text)
                }
            }
        }
    }

    private fun prepareOptionsImage(options: List<Option>) {
        with(binding){
            glOptions.visibility = View.VISIBLE
            for((index,option) in options.withIndex()){
                var imageView = ImageView(requireContext())
                when(index){
                    0 -> imageView = imgOption1
                    1 -> imageView = imgOption2
                    2 -> imageView = imgOption3
                    3 -> imageView = imgOption4
                }
                imageView.setImageBitmap(option.image)
            }
        }
    }

    private fun checkAnswer(selectedAnswer: Int) {
        score = if(selectedAnswer == currentAnswerKey) score + 1 else score

        //show right and wrong answer
        if(selectedAnswer != currentAnswerKey){
//            answerView(selectedAnswer,false)
        }
//        answerView(selectedAnswer,true)
        Handler(Looper.getMainLooper()).postDelayed({
            if(indexQuizQ != listQuizQ.size-1 && indexQuizQ < listQuizQ.size){
                ++indexQuizQ
                ++indexLevel
                prepareQuiz()
            }else{
                //MOVE TO RESULT (END QUIZ)
            }
        },DELAY_TIME)
    }

//    private fun answerView(selectedAnswer: Int, condition: Boolean) {
//        with(binding){
//            when(selectedAnswer){
//                1 -> changeViewSelectedOption(cardOpsi1,condition)
//                2 -> changeViewSelectedOption(cardOpsi2,condition)
//                3 -> changeViewSelectedOption(cardOpsi3,condition)
//                4 -> changeViewSelectedOption(cardOpsi3,condition)
//            }
//        }
//    }

    private fun determineIdOption(numberSequence: Int): Int {
        when(numberSequence){
            1 -> return R.id.option_1
            2 -> return R.id.option_2
            3 -> return R.id.option_3
            else -> return R.id.option_4
        }
    }

    override fun onClick(view: View?) {
        with(binding){
            when(view){
                tvOption1,imgOption1 -> checkAnswer(1)
                tvOption2,imgOption2 -> checkAnswer(2)
                tvOption3,imgOption3 -> checkAnswer(3)
                tvOption4,imgOption4 -> checkAnswer(4)
            }
        }
    }

}