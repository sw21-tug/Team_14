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
import com.example.tutorly.database.UserAutheniticator
import kotlinx.coroutines.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import java.util.*

class TutorListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_list)
    }

    /*
first create a button in mainActivity to get to the TutorsListActivity
second create the TutorListActivity
third create recyclerview to show the Tutorslist in TutorslistActivity
https://developer.android.com/guide/topics/ui/layout/recyclerview
 */

}