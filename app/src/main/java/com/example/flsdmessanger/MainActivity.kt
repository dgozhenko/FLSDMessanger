package com.example.flsdmessanger

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        register_button.setOnClickListener {
            val email = email_edit_register.text.toString()
            val password = password_edit_register.text.toString()

            Log.d("MainActivity", "Email: $email")
            Log.d("MainActivity", "Password: $password")
        }
    }
}