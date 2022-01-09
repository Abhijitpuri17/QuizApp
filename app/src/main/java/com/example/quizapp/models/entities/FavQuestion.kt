package com.example.quizapp.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_questions_table")
data class FavQuestion(
    @ColumnInfo val queText : String,
    @ColumnInfo val ansText : String,
    @ColumnInfo val topic : String,
    @PrimaryKey(autoGenerate = true) val id : Int = 0
)
