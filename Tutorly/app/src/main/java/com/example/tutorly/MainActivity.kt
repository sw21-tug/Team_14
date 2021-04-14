package com.example.tutorly

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.btnTownSelection)

        button.setOnClickListener{
            val intent = Intent(this, TownSelection::class.java)
            startActivity(intent);

        }

    }
}