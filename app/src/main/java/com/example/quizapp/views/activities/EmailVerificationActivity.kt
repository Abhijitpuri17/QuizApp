package com.example.quizapp.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityEmailVerificationBinding

class EmailVerificationActivity : AppCompatActivity()
{

    private lateinit var mBinding : ActivityEmailVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityEmailVerificationBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnGoToLogin.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}