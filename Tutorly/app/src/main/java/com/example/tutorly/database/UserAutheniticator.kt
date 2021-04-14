package com.example.tutorly.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserAutheniticator {
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

    fun getCurrentUser(): FirebaseUser? {
        return currentUser
    }
}