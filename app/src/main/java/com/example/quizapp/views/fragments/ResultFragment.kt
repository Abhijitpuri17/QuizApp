package com.example.quizapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentResultBinding
import com.example.quizapp.utils.Constants


class ResultFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var _binding : FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val score = arguments?.getInt(Constants.SCORE)
        val totalCorrectQuestion = arguments?.getInt(Constants.NUMBER_OF_CORRECT_QUESTIONS)

        _binding = FragmentResultBinding.inflate(layoutInflater)

        _binding.mtvScore.text = score.toString()
        _binding.mtvCorrectQuestionsNumber.text = "$totalCorrectQuestion out of 10 Questions"

        _binding.nbtnTryAgain.setOnClickListener {

           findNavController().navigate(
               R.id.navigation_home, null,
               NavOptions.Builder().setPopUpTo(findNavController().graph.startDestination, true
               ).build())
        }

        return _binding.root
    }


}