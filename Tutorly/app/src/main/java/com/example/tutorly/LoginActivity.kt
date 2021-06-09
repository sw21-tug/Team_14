package com.example.tutorly

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
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
import kotlinx.coroutines.*
import androidx.lifecycle.lifecycleScope
import com.example.tutorly.database.*
import kotlinx.coroutines.GlobalScope
import java.util.*

class LoginActivity : AppCompatActivity() {

    lateinit var locale: Locale

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val changeLang: Button = findViewById(R.id.btn_change_lang)

        val loginButton: Button = findViewById(R.id.btn_login)
        val gotoRegisterText: TextView = findViewById(R.id.txt_gotoRegister)
        val inputEmail: EditText = findViewById(R.id.input_username_login)
        val inputPassword: EditText = findViewById(R.id.input_password_login)

        changeLang.setOnClickListener {
            val list = arrayOf("English", "Russian")
            val builder = AlertDialog.Builder(this@LoginActivity)
            builder.setTitle(R.string.choose_lang)

            builder.setSingleChoiceItems(list, -1) { dialog, which ->
                if (which == 0) {
                    Translation.changeLang("default", this)
                    recreate()
                }
                else if (which == 1) {
                    Translation.changeLang("kv", this)
                    recreate()
                }
                dialog.dismiss()
            }
            builder.show()
        }

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
            lifecycleScope.launch {
                try {
                    UserAutheniticator().userSignIn(email,password)?.let {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    } ?: run {
                        Toast.makeText(
                            this@LoginActivity,
                            "Login failed!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login failed!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }

    fun changeLang(localeName: String, context: Context) {
        locale = Locale(localeName)
        val res = context.resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = locale
        res.updateConfiguration(conf, dm)
    }

}