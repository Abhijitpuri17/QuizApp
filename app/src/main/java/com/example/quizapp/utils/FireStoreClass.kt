package com.example.quizapp.utils

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.quizapp.adapters.QuizCategoryAdapter
import com.example.quizapp.models.Question
import com.example.quizapp.models.User
import com.example.quizapp.views.activities.SignUpActivity
import com.example.quizapp.views.fragments.QuizFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FireStoreClass
{


    fun saveFavQuestion(question: Question, fragment: Fragment)
    {
        val mp = HashMap<String, Any>()

        FirebaseFirestore.getInstance().collection(
            Constants.FAVOURITE_QUESTIONS
        ).document(getUserId())
            .get().addOnSuccessListener {

                val queList : ArrayList<Question> = it["questions"] as ArrayList<Question>
                queList.add(question)

                mp[Constants.QUESTION_LIST] = queList

                FirebaseFirestore.getInstance().collection(
                    Constants.FAVOURITE_QUESTIONS
                ).document(
                    getUserId()
                ).update(mp)
                    .addOnCompleteListener {
                    Toast.makeText(
                        fragment.requireContext(),
                        "Question Saved", Toast.LENGTH_SHORT
                    ).show()
                }

            }
    }


    private fun getUserId(): String
    {
        val user = FirebaseAuth.getInstance().currentUser

        var id = ""

        if (user != null) id = user.uid

        return id

    }



    fun saveUserInfo(user: User, activity: Activity)
    {
        FirebaseFirestore.getInstance().collection(Constants.USER)
            .document(user.id).set(user, SetOptions.merge()).
            addOnCompleteListener {

                if (it.isSuccessful){
                    if (activity is SignUpActivity)
                    {
                        activity.dataSaveSuccess()
                    }
                } else{
                    if (activity is SignUpActivity){
                        activity.hideProgressDialog()
                    }

                    Toast.makeText(activity, "Error while saving data", Toast.LENGTH_LONG).show()
                }

            }.addOnFailureListener {

                if (activity is SignUpActivity){
                    activity.hideProgressDialog()
                }

                Toast.makeText(activity, "Error while saving data", Toast.LENGTH_LONG).show()
            }


    }

}