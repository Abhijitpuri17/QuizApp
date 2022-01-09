package com.example.quizapp.application

import android.app.Application
import com.example.quizapp.models.database.FavQuestionDatabase
import com.example.quizapp.models.database.FavQuestionRepository

class FavQuestionApplication: Application()
{

    private val database by lazy {FavQuestionDatabase.getDatabase(this@FavQuestionApplication)}

    val repository by lazy { FavQuestionRepository(database.favQuestionsDao()) }

}
