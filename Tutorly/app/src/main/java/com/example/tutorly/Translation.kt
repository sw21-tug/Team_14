package com.example.tutorly

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import java.util.*

class Translation : AppCompatActivity(){

    lateinit var locale: Locale

    fun changeLang(localeName: String, context: Context) {
        locale = Locale(localeName)
        val res = context.resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = locale
        res.updateConfiguration(conf, dm)
    }
}