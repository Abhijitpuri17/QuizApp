package com.example.quizapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.adapters.FavQuestionAdapter
import com.example.quizapp.application.FavQuestionApplication
import com.example.quizapp.databinding.FragmentFavouritesBinding
import com.example.quizapp.models.Question
import com.example.quizapp.models.entities.FavQuestion
import com.example.quizapp.viewmodels.FavQuestionViewModel
import com.example.quizapp.viewmodels.FavQuestionsViewModelFactory

class FavouritesFragment : Fragment() {


    private var _binding: FragmentFavouritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val favQuestionsViewModel: FavQuestionViewModel by viewModels {
        FavQuestionsViewModelFactory((requireActivity().application as FavQuestionApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = FavQuestionAdapter(this)

        _binding!!.rvFavouriteQuestions.adapter = adapter

        _binding!!.rvFavouriteQuestions.layoutManager = LinearLayoutManager(requireContext())

        favQuestionsViewModel.allQuestionsList.observe(requireActivity()){

                adapter.getData(it as ArrayList<FavQuestion>)

        }

        return root
    }

    fun deleteQue(que : FavQuestion)
    {
        favQuestionsViewModel.delete(que)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}