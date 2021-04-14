package com.example.tutorly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.findNavController

class LoginAActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_a)

        val signInButton: Button = findViewById(R.id.login)
        /*button.setOnClickListener{
            findNavController().navigate(R.id.loginToRegister)

        }

        val handleTextClick = object : View.OnClickListener {
            override fun onClick(view: View) {
                //view.findViewById<Button>(R.id.login).setText("hi")
                findNavController().navigate(R.id.loginToRegister)
            }
        }

        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.login).setOnClickListener(handleTextClick)*/
    }

    override fun onStart() {
        super.onStart()
        val signInButton: Button = findViewById(R.id.login)
        signInButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginAActivity_to_registerAActivity)
        }
    }
}