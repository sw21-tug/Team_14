package com.example.tutorly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.tutorly.database.User
import com.example.tutorly.database.UserAutheniticator

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val gotoSigninText: TextView = findViewById(R.id.txt_gotoSignin)
        val inputEmail: EditText = findViewById(R.id.username_reg)
        val inputPassword: EditText = findViewById(R.id.password_reg)
        val inputConfirmPassword: EditText = findViewById(R.id.confirm_password_reg)
        val regButton: Button = findViewById(R.id.btn_register)
        val firstname: EditText = findViewById(R.id.input_firstname)
        val lastname: EditText = findViewById(R.id.input_lastname)

        gotoSigninText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        regButton.setOnClickListener {
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(inputEmail.text.toString()).matches())
            {
                Toast.makeText(
                    this@RegisterActivity,
                    "Please enter valid email!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (inputPassword.text.toString().trim{it <= ' '}.length < 6)
            {
                Toast.makeText(
                    this@RegisterActivity,
                    "Please enter valid password with length greater 6!",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (inputConfirmPassword.text.toString() != inputPassword.text.toString())
            {
                Toast.makeText(
                    this@RegisterActivity,
                    "Passwords do not match!",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (firstname.text.toString().trim {it <= ' '}.isEmpty() || lastname.text.toString().trim {it <= ' '}.isEmpty())
            {
                Toast.makeText(
                    this@RegisterActivity,
                    "Name is empty!",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val email: String = inputEmail.text.toString().trim {it <= ' '}
            val password: String = inputPassword.text.toString().trim {it <= ' '}
            val firstname_val: String = firstname.text.toString().trim {it <= ' '}
            val lastname_val: String = lastname.text.toString().trim {it <= ' '}

            UserAutheniticator().createUserAccount(User(firstname_val,lastname_val,email,password))
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
}