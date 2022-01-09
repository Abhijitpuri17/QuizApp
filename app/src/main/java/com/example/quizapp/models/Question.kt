package com.example.quizapp.models

data class Question(
    val queText : String,
    val option1 : String,
    val option2 : String,
    val option3 : String,
    val option4 : String,
    val correctOption : Int,
)
