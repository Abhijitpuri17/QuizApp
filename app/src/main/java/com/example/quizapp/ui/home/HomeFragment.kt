package com.example.quizapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizapp.QuizCategory
import com.example.quizapp.adapters.QuizCategoryAdapter
import com.example.quizapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter : QuizCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rvCategories = _binding!!.rvQuizCategories

        adapter = QuizCategoryAdapter(this)

        rvCategories.adapter = adapter

        rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)

        quizCatData()

        return root
    }


    fun quizCatData()
    {
        val quizList = arrayOf(
            QuizCategory("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZdgV5S9C5GWwq_c7Zz0iuevDw0j4oR6Igzg&usqp=CAU", "Computers"),
            QuizCategory("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnq_GZEk9eq6q9Wcjqn7xUU63bOxbotc8psQ&usqp=CAU", "Mathematics"),
            QuizCategory("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe7VKBBpAxmSjjoDxk5iUnjV3GS3MA9Fo55g&usqp=CAU", "Mythology"),
            QuizCategory("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSovM8Vjr1A2zEK5yfqTv1tAn8hT-silDWAHA&usqp=CAU","Geography"),
            QuizCategory("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRu7d5vYn0Kl0-LAs0lrmK91o_Df9BD6idGnQ&usqp=CAU","History"),
            QuizCategory("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTlHQStN-DkzZlLDxCIkV30LDiDftuswHBTyw&usqp=CAU","Politics"),
        )

        adapter.getData(quizList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}