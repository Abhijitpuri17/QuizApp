package com.example.quizapp

import android.app.Activity
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FireStoreClass
{

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