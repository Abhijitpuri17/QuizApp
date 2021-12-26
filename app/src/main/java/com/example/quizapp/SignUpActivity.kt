package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivitySignUpBinding
import soup.neumorphism.NeumorphButton

class SignUpActivity : AppCompatActivity()

{

    override fun onCreate(savedInstanceState: Bundle?)

    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up)

        val goToLoginButton = findViewById<NeumorphButton>(R.id.nbtn_go_to_login)

        goToLoginButton.setOnClickListener {
            finish()
        }




    }
}