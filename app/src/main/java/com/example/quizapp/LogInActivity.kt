package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quizapp.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import soup.neumorphism.NeumorphButton

class LogInActivity : BaseActivity() {


    private lateinit var etEmail : EditText
    private lateinit var etPassword: EditText

    private lateinit var mBinding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mBinding = ActivityLogInBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        val goToSignUpButton = mBinding.btnGoToSignup
        // if user doesn't have an account. Go to sign up page
        goToSignUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        etEmail =  mBinding.etEmail
        etPassword = mBinding.etPassword

        val loginButton = mBinding.nbtnLogin

        /**
         * if user clicks on login button
         */
        loginButton.setOnClickListener {
            loginUser()
        }

    }

    override fun onStart() {
        super.onStart()

        /**
         * if user is already logged in send him/her to main acctivity
         */
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null && user.isEmailVerified)
        {
            logInSuccess()
        }


    }


    /**
     * checks if all the entries are filled or not
     */
    private fun validateEntries(email: String, password: String): Boolean
    {
        if (TextUtils.isEmpty(email)) return false

        if (TextUtils.isEmpty(password)) return false

        return true
    }


    private fun logInSuccess()
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun loginUser()
    {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()


        /**
         * if all the entries are filled login user
         */
        if (validateEntries(email, password))
        {

            /**
             * show loading dialog while logging in user
             */
            showProgressDialog()


            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).
                addOnCompleteListener {

                    /**
                     * hide loading dialog from screen
                     */
                    hideProgressDialog()

                    /**
                     * if login is successful
                     */
                    if (it.isSuccessful) {

                        /**
                         * currently logged in user
                         */
                        val user = it.result.user!!

                        /**
                         * check if he/she verified email or not
                         */
                        if (user.isEmailVerified)
                        {
                            /**
                             * if email is verified then send user to main activity
                             */
                            logInSuccess()

                        }
                        /**
                         * if email is not verified
                         */
                        else
                        {
                            Toast.makeText(
                                this, "Please verify your email", Toast.LENGTH_LONG).
                                show()
                        }

                    }
                }

                /**
                 * if some error occurs while logging in
                 */
                .addOnFailureListener {

                    /**
                     * hide loading dialog from screen
                     */
                    hideProgressDialog()

                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                 }


        }



    }

}