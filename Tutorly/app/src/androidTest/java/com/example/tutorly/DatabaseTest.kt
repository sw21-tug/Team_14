package com.example.tutorly

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

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
import kotlinx.coroutines.tasks.await

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

class DatabaseTest
{

    @Test
    fun checkUserSignedInTest()
    {
        var testUser = User("Musterman", "Mustermann", "mustermann@tugraz.at", "1234")
        val testDataVar = createUserAccount(testUser)
        assert(checkIfNull(testDataVar))
    }

    fun checkIfNull(user : User)
    {
        if(user != null) true else false
    }

    @Test
    fun signInValidationTest()
    {
        var testUser = User("Musterman", "Mustermann", "mustermann@tugraz.at", "1234")
        var userEmail = testUser.getEmail()
        var userPassword = testUser.getPassword()
        assert(userSignIn(userEmail, userPassword))
    }

    @Test
    fun getUserTest()
    {
        var testUser = User("Musterman", "Mustermann", "mustermann@tugraz.at", "1234")
        var userPassword = testUser.getPassword()
        val testDataVar = createUserAccount(testUser)

        var currentUser = getCurrentUser(testUser)
        if(currentUser.getEmail() == "mustermann@tugraz.at" &&
                currentUser.getPassword() == "1234")
        {
            assert(true)
        }
        else
        {
            assert(false)
        }
    }

    fun getCurrentUser(user : User)
    {
        return user
    }

}