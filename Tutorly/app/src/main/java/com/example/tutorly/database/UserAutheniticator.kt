package com.example.tutorly.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tutorly.RegisterActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class UserAutheniticator : Activity() {
    private var userAusthenticator : FirebaseAuth = FirebaseAuth.getInstance()
    private var currentUser : FirebaseUser? = userAusthenticator.getCurrentUser()


    fun isUserSignedIn(): Boolean {
        return if (currentUser != null) true else false
    }

    fun createUserAccount(user: User): FirebaseUser? {
        userAusthenticator.createUserWithEmailAndPassword(user.getEmail(), user.getpassword()).addOnSuccessListener {
            task ->
            currentUser = task.user
            user.setUserID(currentUser!!.getIdToken(false).result.toString())

        }
        return currentUser
    }

    fun userSignIn(email: String,password: String): Boolean
    {
        var result : Boolean = false

       userAusthenticator.signInWithEmailAndPassword(email, password).addOnCompleteListener()
       {
            task ->
            result = task.isSuccessful
            println(result.toString())
        }

        println(result.toString())
        return result;


    }

    fun getCurrentUser(): FirebaseUser? {
        return currentUser
    }
}