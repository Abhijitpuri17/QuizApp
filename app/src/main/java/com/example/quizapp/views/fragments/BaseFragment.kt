package com.example.quizapp.views.fragments

import android.app.Dialog
import androidx.fragment.app.Fragment
import com.example.quizapp.R

open class BaseFragment: Fragment()
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
        mProgressDialog = Dialog(requireContext())

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