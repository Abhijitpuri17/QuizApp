package com.example.quizapp.views.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.R

/**
 * Add the functions which we will use multiple times in the app
 * int this class and inherit our activities from this class
 * so that we don't have to write functions multiple times
 */
open class BaseActivity : AppCompatActivity()
{

    private lateinit var mProgressDialog : Dialog

    /**
     * If some task in background is taking time user should see loading dialog
     */
    fun showProgressDialog()
    {
        /**
         * create a dialog
         */
        mProgressDialog = Dialog(this)

        /**
         * Set the layout for dialog
         */
        mProgressDialog.setContentView(R.layout.loading_dialog_layout)

        /**
         * Dialog should not be cancelled until task is completed
         * so set cancelable and canceledOnTouchOutside as false
         */
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        /**
         * show the dialog
         */
        mProgressDialog.show()
    }

    fun hideProgressDialog()
    {
        /**
         * hide the dialog
         */
        mProgressDialog.dismiss()
    }


}