package com.kontrakanprojects.appgamequiz.view.quiz

import android.content.Context
import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.graphics.Bitmap
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.*
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.model.Option
import com.kontrakanprojects.appgamequiz.data.model.Question
import com.kontrakanprojects.appgamequiz.databinding.FragmentQuizBinding
import com.kontrakanprojects.appgamequiz.util.DataQuiz
import com.kontrakanprojects.appgamequiz.view.quiz.EndQuizActivity.Companion.COUNT_CORRECT_ANSWER
import com.kontrakanprojects.appgamequiz.view.quiz.EndQuizActivity.Companion.QUIZ_Q_SIZE

class QuizFragment : Fragment(),View.OnClickListener {
    private lateinit var binding: FragmentQuizBinding
    private lateinit var listQuizQ: ArrayList<Question>
    private var indexQuizQ = 0
    private var currentAnswerKey = 0
    private var score = 0
    private var indexLevel = 0
    private val DELAY_TIME = 1000L
    private lateinit var vibrator: Vibrator


    lateinit var audioRaw: AssetFileDescriptor
    lateinit var mediaPlayer: MediaPlayer

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

        vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

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
            if(currentQuizQ.images != null){
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
            tvQuizQuestion.text = currentQuizQ.text
            //options
            val isImage = currentQuizQ.options.get(0).image is Bitmap
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

            //default options view
            defaultOptionsView()
        }
    }

    private fun defaultOptionsView() {
        with(binding){
            val textOptions = ArrayList<TextView>(
                listOf(tvOption1,tvOption2,tvOption3,tvOption4)
            )
            for (tOpt in textOptions) {
                tOpt.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_option)
            }

            val imgOptions = ArrayList<ImageView>(
                listOf(imgOption1,imgOption2,imgOption3,imgOption4)
            )
            for (imgOpt in imgOptions) {
                imgOpt.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_img_option)
            }
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

    private fun checkAnswer(selectedAnswer: Int,isText :Boolean = false, isImage: Boolean = false) {
        score = if(selectedAnswer == currentAnswerKey) {
            val point = listQuizQ.get(currentAnswerKey).points
            score + point
        } else { score }

        //show right and wrong answer
        if(selectedAnswer != currentAnswerKey){
            if(isText){
                changeStyleBgOptionView(selectedAnswer,"text")
            }else{
                changeStyleBgOptionView(selectedAnswer,"image")
            }
            vibrate()
        }else{
            if(isText){
                changeStyleBgOptionView(selectedAnswer,"text",true)
            }else{
                changeStyleBgOptionView(selectedAnswer,"image",true)
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
            if(indexQuizQ != listQuizQ.size-1 && indexQuizQ < listQuizQ.size){
                ++indexQuizQ
                ++indexLevel
                prepareQuiz()
            }else{
                moveToResult()
            }
        },DELAY_TIME)
    }

    private fun moveToResult() {
        val intent = Intent(activity,EndQuizActivity::class.java).apply{
            putExtra(COUNT_CORRECT_ANSWER,score)
            putExtra(QUIZ_Q_SIZE,listQuizQ.size)
        }
        startActivity(intent)
        findNavController().popBackStack()
    }

    private fun changeStyleBgOptionView(selectedAnswer: Int, type: String, isRight: Boolean = false) {
        with(binding){
            when(type){
                "text" -> {
                    if(!isRight) {
                        when(selectedAnswer){
                            1 -> tvOption1.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_option_red)
                            2 -> tvOption2.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_option_red)
                            3 -> tvOption3.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_option_red)
                            4 -> tvOption4.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_option_red)
                        }
                    }else{
                        when(selectedAnswer){
                            1 -> tvOption1.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_option_selected)
                            2 -> tvOption2.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_option_selected)
                            3 -> tvOption3.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_option_selected)
                            4 -> tvOption4.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_option_selected)
                        }
                    }
                }
                else -> {
                    if(!isRight){
                        when(selectedAnswer){
                            1 -> imgOption1.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_img_option_red)
                            2 -> imgOption2.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_img_option_red)
                            3 -> imgOption3.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_img_option_red)
                            4 -> imgOption4.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_img_option_red)
                        }
                    }else{
                        when(selectedAnswer){
                            1 -> imgOption1.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_img_option_selected)
                            2 -> imgOption2.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_img_option_selected)
                            3 -> imgOption3.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_img_option_selected)
                            4 -> imgOption4.background = ContextCompat.getDrawable(requireContext(),R.drawable.bg_quiz_img_option_selected)
                        }
                    }
                }
            }
        }
    }

    override fun onClick(view: View?) {
        with(binding){
            when(view){
                tvOption1 -> checkAnswer(1,true)
                imgOption1 -> checkAnswer(1,false,true)
                tvOption2 -> checkAnswer(2,true)
                imgOption2 -> checkAnswer(2,false,true)
                tvOption3 -> checkAnswer(3,true)
                imgOption3 -> checkAnswer(3,false,true)
                tvOption4 -> checkAnswer(4,true)
                imgOption4 -> checkAnswer(4,false,true)
            }
        }
    }

    private fun vibrate(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }

}
