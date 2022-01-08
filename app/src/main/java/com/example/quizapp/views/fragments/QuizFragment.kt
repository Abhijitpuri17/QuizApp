package com.example.quizapp.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityHomeBinding
import com.example.quizapp.databinding.FragmentQuizBinding
import com.example.quizapp.models.Question
import com.example.quizapp.utils.Constants
import com.example.quizapp.views.activities.HomeActivity
import soup.neumorphism.NeumorphButton

class QuizFragment : BaseFragment() {

    private var _binding : FragmentQuizBinding? = null

    private lateinit var questionList: ArrayList<Question>
    private var currentQuestionNum = 0
    private var score = 0
    var totalQuestion : Int = 0
    private var chosenOption = 0
    private lateinit var currQuestion : Question
    private var shouldMoveToNextQuestion = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        showProgressDialog()

        _binding = FragmentQuizBinding.inflate(layoutInflater)


        val categoryName = arguments?.getString(Constants.CATEGORY_NAME)
        val categoryImageLink = arguments?.getString(Constants.CATEGORY_IMAGE_LINK)
        val apiURL = arguments?.getString(Constants.API_URL)

        _binding!!.tvCategoryName.text = categoryName
        Glide.with(requireContext()).load(categoryImageLink).into(_binding!!.ivQuizCategoryImage)

        loadQuestions(apiURL!!)

        _binding!!.nbtnSubmit.setOnClickListener {
            if (shouldMoveToNextQuestion)
                displayNextQuestion()
            else
                submitAnswer()
        }

        _binding!!.nbtnOption1.setOnClickListener {
            chooseOption(it)
        }
        _binding!!.nbtnOption2.setOnClickListener {
            chooseOption(it)
        }
        _binding!!.nbtnOption3.setOnClickListener {
            chooseOption(it)
        }
        _binding!!.nbtnOption4.setOnClickListener {
            chooseOption(it)
        }

      return _binding!!.root
    }



    private fun chooseOption(v: View)
    {

        if (shouldMoveToNextQuestion) return

        if (_binding!!.nbtnOption1 == v){
            chosenOption = 1
            v.setTextColor(resources.getColor(R.color.orange_light))
            v.setShapeType(1)
        }
        else{
             _binding!!.nbtnOption1.setShapeType(2)
            _binding!!.nbtnOption1.setTextColor(resources.getColor(R.color.grey))
        }

        if (_binding!!.nbtnOption2 == v){
            chosenOption = 2
            v.setShapeType(1)
            v.setTextColor(resources.getColor(R.color.orange_light))
        }
        else{
            _binding!!.nbtnOption2.setShapeType(2)
            _binding!!.nbtnOption2.setTextColor(resources.getColor(R.color.grey))
        }

        if (_binding!!.nbtnOption3 == v){
            chosenOption = 3
            v.setTextColor(resources.getColor(R.color.orange_light))
            v.setShapeType(1)
        }
        else{
            _binding!!.nbtnOption3.setShapeType(2)
            _binding!!.nbtnOption3.setTextColor(resources.getColor(R.color.grey))
        }

        if (_binding!!.nbtnOption4 == v){
            chosenOption = 4
            v.setShapeType(1)
            v.setTextColor(resources.getColor(R.color.orange_light))
        }
        else{
            _binding!!.nbtnOption4.setShapeType(2)
            _binding!!.nbtnOption4.setTextColor(resources.getColor(R.color.grey))
        }
    }

    private fun submitAnswer()
    {
        if (chosenOption == 0){
            Toast.makeText(requireContext(), "Please select an answer", Toast.LENGTH_SHORT).show()
            return
        }

        if (currQuestion.correctOption == 1){
            _binding!!.nbtnOption1.setTextColor(resources.getColor(android.R.color.holo_green_dark))
        } else if (chosenOption == 1){
            _binding!!.nbtnOption1.setTextColor(resources.getColor(android.R.color.holo_red_dark))
        }

        if (currQuestion.correctOption == 2){
            _binding!!.nbtnOption2.setTextColor(resources.getColor(android.R.color.holo_green_dark))
        } else if (chosenOption == 2){
            _binding!!.nbtnOption2.setTextColor(resources.getColor(android.R.color.holo_red_dark))
        }

        if (currQuestion.correctOption == 3){
            _binding!!.nbtnOption3.setTextColor(resources.getColor(android.R.color.holo_green_dark))
        } else if (chosenOption == 3){
            _binding!!.nbtnOption3.setTextColor(resources.getColor(android.R.color.holo_red_dark))
        }

        if (currQuestion.correctOption == 4){
            _binding!!.nbtnOption4.setTextColor(resources.getColor(android.R.color.holo_green_dark))
        } else if (chosenOption == 4){
            _binding!!.nbtnOption4.setTextColor(resources.getColor(android.R.color.holo_red_dark))
        }

        if (chosenOption == currQuestion.correctOption) score++

        currentQuestionNum++


        if (currentQuestionNum >= totalQuestion )
        {

            val bundle = bundleOf(
                Constants.NUMBER_OF_CORRECT_QUESTIONS to score,
                Constants.SCORE to score*10
            )

            findNavController().navigate(R.id.action_quizFragment2_to_resultFragment, bundle)

        }
        shouldMoveToNextQuestion = true
        _binding!!.nbtnSubmit.text = "NEXT QUESTION"

    }

    private fun displayNextQuestion()
    {

        chosenOption = 0

        _binding!!.nbtnOption1.setShapeType(0)
        _binding!!.nbtnOption2.setShapeType(0)
        _binding!!.nbtnOption3.setShapeType(0)
        _binding!!.nbtnOption4.setShapeType(0)

        _binding!!.nbtnOption1.setTextColor(resources.getColor(R.color.grey))
        _binding!!.nbtnOption2.setTextColor(resources.getColor(R.color.grey))
        _binding!!.nbtnOption3.setTextColor(resources.getColor(R.color.grey))
        _binding!!.nbtnOption4.setTextColor(resources.getColor(R.color.grey))



        _binding!!.nbtnQuestionNumber.text = (currentQuestionNum+1).toString()
        _binding!!.tvQuestionNumberLabel.text = "${currentQuestionNum+1}/${totalQuestion}"


        if (currentQuestionNum < totalQuestion){
            currQuestion = questionList[currentQuestionNum]
            _binding!!.tvQuestion.text = currQuestion.queText
            _binding!!.nbtnOption1.text = currQuestion.option1
            _binding!!.nbtnOption2.text = currQuestion.option2
            _binding!!.nbtnOption3.text = currQuestion.option3
            _binding!!.nbtnOption4.text = currQuestion.option4

            shouldMoveToNextQuestion = false
            _binding!!.nbtnSubmit.text = "SUBMIT"

        } else{

                val bundle = bundleOf(
                    Constants.NUMBER_OF_CORRECT_QUESTIONS to score,
                    Constants.SCORE to score*10
                )

                findNavController().navigate(R.id.action_quizFragment2_to_resultFragment, bundle)

        }
    }


    private fun loadQuestions(url: String)
    {

        questionList = ArrayList()
        val queue = Volley.newRequestQueue(requireContext())

        val jsonObj = JsonObjectRequest(url, {

            Log.e("API CALL", it.toString())
            val queArr = it.getJSONArray("results")

            for (i in 0 until queArr.length()){
                val que = queArr.getJSONObject(i)

                val options = que.getJSONArray("incorrect_answers")
                val optionsArr = mutableListOf<String>()

                for (j in 0 until options.length()){
                    optionsArr.add(options.getString(j))
                }

                val correctAns = que.getString("correct_answer")

                optionsArr.add(correctAns)

                optionsArr.shuffle()

                var correctOption = 0

                for (k in 0 until optionsArr.size){
                    if (optionsArr[k] == correctAns) correctOption = k+1
                }

                val q = Question(que.getString("question"), optionsArr[0], optionsArr[1], optionsArr[2], optionsArr[3], correctOption)

                questionList.add(q)

            }

            totalQuestion = questionList.size
            hideProgressDialog()
            displayNextQuestion()

        }, {
            it.printStackTrace()
        })

        queue.add(jsonObj)
    }










}