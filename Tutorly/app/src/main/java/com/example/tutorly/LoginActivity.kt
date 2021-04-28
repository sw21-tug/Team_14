package com.example.tutorly

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import org.w3c.dom.Text

import androidx.lifecycle.Observer
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.tutorly.database.UserAutheniticator

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.btn_login)
        val gotoRegisterText: TextView = findViewById(R.id.txt_gotoRegister)
        val inputEmail: EditText = findViewById(R.id.input_username_login)
        val inputPassword: EditText = findViewById(R.id.input_password_login)

        gotoRegisterText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(inputEmail.text.toString()).matches())
            {
                Toast.makeText(
                        this@LoginActivity,
                        "Please enter valid email!",
                        Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (inputPassword.text.toString().trim{it <= ' '}.length < 6)
            {
                Toast.makeText(
                        this@LoginActivity,
                        "Please enter valid password with length greater 6!",
                        Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val email: String = inputEmail.text.toString().trim {it <= ' '}
            val password: String = inputPassword.text.toString().trim {it <= ' '}
            UserAutheniticator().userSignIn(email,password)
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }
    }


}