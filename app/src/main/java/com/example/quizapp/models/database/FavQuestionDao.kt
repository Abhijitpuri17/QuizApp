package com.example.quizapp.models.database

import androidx.room.*
import com.example.quizapp.models.Question
import com.example.quizapp.models.entities.FavQuestion
import kotlinx.coroutines.flow.Flow

@Dao
interface FavQuestionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favQue : FavQuestion)

    @Query("SELECT * FROM fav_questions_table ORDER BY ID")
    fun getAllQuestions() : Flow<List<FavQuestion>>

    @Delete
    suspend fun delete(note: FavQuestion)

}