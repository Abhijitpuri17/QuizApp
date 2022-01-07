package com.example.quizapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizapp.R
import com.example.quizapp.models.QuizCategory
import com.example.quizapp.adapters.QuizCategoryAdapter
import com.example.quizapp.databinding.ActivityHomeBinding
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.databinding.FragmentHomeBinding
import com.example.quizapp.utils.Constants
import com.example.quizapp.views.activities.HomeActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter : QuizCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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

    override fun onResume() {
        super.onResume()

        if (requireActivity() is HomeActivity){
            (activity as HomeActivity).showBottomNav()
        }

    }


    fun goToQuiz(quizCategory: QuizCategory)
    {

        val categoryName = quizCategory.categoryName
        val categoryImageLink = quizCategory.imageLink

        val apiURL = "https://opentdb.com/api.php?amount=10&category=18&type=multiple"

        val bundle = bundleOf(
            Constants.CATEGORY_NAME to categoryName,
            Constants.CATEGORY_IMAGE_LINK to categoryImageLink,
            Constants.API_URL to apiURL
        )

        findNavController().navigate(R.id.action_navigation_home_to_quizFragment2, bundle)

        if (requireActivity() is HomeActivity){
            (activity as HomeActivity).hideBottomNav()
        }

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}