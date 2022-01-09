package com.example.quizapp.viewmodels

import android.widget.Toast
import androidx.lifecycle.*
import com.example.quizapp.models.database.FavQuestionRepository
import com.example.quizapp.models.entities.FavQuestion
import com.example.quizapp.views.fragments.QuizFragment
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class FavQuestionViewModel(private val repository: FavQuestionRepository): ViewModel()
{

    fun insert(question: FavQuestion)  = viewModelScope.launch {
        //Toast.makeText(QuizFragment().requireContext(), "ViewModel", Toast.LENGTH_SHORT).show()
        repository.insert(question)
    }

    fun delete(question: FavQuestion) = viewModelScope.launch {
        repository.delete(question)
    }

    val allQuestionsList : LiveData<List<FavQuestion>> = repository.allQuestions.asLiveData()

}

class FavQuestionsViewModelFactory(private val repository: FavQuestionRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavQuestionViewModel::class.java)){
            @Suppress("Unchecked_cast")
            return FavQuestionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")

    }

}