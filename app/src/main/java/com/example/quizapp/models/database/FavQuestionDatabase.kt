package com.example.quizapp.models.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.quizapp.models.entities.FavQuestion

@Database(entities = [FavQuestion::class], version = 1, exportSchema = false)
abstract class FavQuestionDatabase : RoomDatabase(){

    abstract fun favQuestionsDao(): FavQuestionDao

    companion object{

        @Volatile
        private var INSTANCE: FavQuestionDatabase? = null

        fun getDatabase(context: Context): FavQuestionDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    FavQuestionDatabase::class.java,
                    "fav_questions_database").build()

                INSTANCE = instance
                instance
            }
        }
    }
}