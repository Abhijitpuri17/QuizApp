package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import soup.neumorphism.NeumorphButton

class LogInActivity : AppCompatActivity() {


    private lateinit var etEmail : EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val goToSignUpButton = findViewById<TextView>(R.id.btn_go_to_signup)

        // if user doesn't have an account. Go to sign up page
        goToSignUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)

        val loginButton = findViewById<NeumorphButton>(R.id.nbtn_login)

        /**
         * if user clicks on login button
         */
        loginButton.setOnClickListener {
            loginUser()
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


    private fun loginUser()
    {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()


        /**
         * if all the entries are filled login user
         */
        if (validateEntries(email, password))
        {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).
                addOnCompleteListener {

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
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
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

                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                 }


        }



    }

}