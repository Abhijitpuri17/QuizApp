package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityLogInBinding
import soup.neumorphism.NeumorphButton

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val goToSignUpButton = findViewById<NeumorphButton>(R.id.nbtn_go_to_signup)

        // if user doesn't have an account. Go to sign up page
        goToSignUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }



    }
}