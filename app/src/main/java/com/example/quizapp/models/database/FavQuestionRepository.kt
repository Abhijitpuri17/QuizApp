package com.example.quizapp.models.database

import androidx.annotation.WorkerThread
import com.example.quizapp.models.Question
import com.example.quizapp.models.entities.FavQuestion
import kotlinx.coroutines.flow.Flow

class FavQuestionRepository(private val favQuestionsDao : FavQuestionDao)
{

    val allQuestions : Flow<List<FavQuestion>> = favQuestionsDao.getAllQuestions()

    @WorkerThread
    suspend fun insert(question: FavQuestion)
    {
        favQuestionsDao.insert(question)
    }

    @WorkerThread
    suspend fun delete(question: FavQuestion){
        favQuestionsDao.delete(question)
    }
}