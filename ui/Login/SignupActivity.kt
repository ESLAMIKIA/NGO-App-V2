package com.example.ngo.ui.Login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ngo.R
import com.example.ngo.ui.Login.LoginActivity

class SignupActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.hide() // ← مخفی کردن ActionBar

        val emailEditText = findViewById<EditText>(R.id.etEmail)
        val passwordEditText = findViewById<EditText>(R.id.etPassword)
        val signupButton = findViewById<Button>(R.id.btnSignup)
        val loginButton = findViewById<Button>(R.id.btnLogin)

        signupButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("email", email)
                editor.putString("password", password)
                editor.apply()

                Toast.makeText(this, "register was successful", Toast.LENGTH_SHORT).show()

//                 اگر خواستی بره به صفحه لاگین یا اصلی
                 startActivity(Intent(this, LoginActivity::class.java))
                 finish()

            } else {
                Toast.makeText(this, "please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

    loginButton.setOnClickListener{
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }


    }
}
