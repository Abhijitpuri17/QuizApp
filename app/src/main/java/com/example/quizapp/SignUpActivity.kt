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

class SignUpActivity : AppCompatActivity()

{


    private lateinit var etName : EditText
    private lateinit var etEmail : EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?)

    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up)

        val goToLoginButton = findViewById<TextView>(R.id.btn_go_to_login)

        etName = findViewById(R.id.et_name)

        etEmail = findViewById(R.id.et_email)

        etPassword = findViewById(R.id.et_password)


        goToLoginButton.setOnClickListener {
            finish()
        }

        val signUpButton = findViewById<NeumorphButton>(R.id.nbtn_login)

        signUpButton.setOnClickListener {
            signUpUser()
        }


    }


    private fun validateEntries(name: String, email: String, password: String): Boolean
    {
        if (TextUtils.isEmpty(name)) return false

        if (TextUtils.isEmpty(email)) return false

        if (TextUtils.isEmpty(password)) return false

        return true
    }


    private fun signUpUser()
    {

        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        /** checks if all the entries are filled or not
         */
        if (validateEntries(name, email, password))
        {
            FirebaseAuth.getInstance().
                createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {

                    /** if user account is created successfully
                     */
                    if (it.isSuccessful){

                        val user = FirebaseAuth.getInstance().currentUser!!

                        // add user information to fire-store

                        user.sendEmailVerification()
                            .addOnCompleteListener {

                                /** if email is sent successfully
                                 */
                                if (it.isSuccessful)
                                {
                                    Toast.makeText(
                                        this, "Verification email has been sent to your registered email",
                                        Toast.LENGTH_LONG).show()

                                    FirebaseAuth.getInstance().signOut()

                                    val intent = Intent(this, LogInActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                                else {

                                    /**
                                     * if user entered invalid email
                                     */
                                    FirebaseAuth.getInstance().signOut()

                                    Toast.makeText(
                                        this, "Please enter an valid email id",
                                        Toast.LENGTH_LONG).show()
                                }

                            }
                            /** if some error occurs while sending email
                             */
                            .addOnFailureListener {
                                Toast.makeText(
                                    this, it.localizedMessage,
                                    Toast.LENGTH_LONG).show()
                            }
                    }



                }
                /**
                 * if some error occurs while creating account
                 */
                .addOnFailureListener {

                    Toast.makeText(
                        this, it.localizedMessage,
                        Toast.LENGTH_LONG).
                        show()
                }

        }

    }


}