package com.example.tutorly
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.tutorly.database.User
import com.example.tutorly.database.UserAutheniticator
import kotlinx.coroutines.*


import org.junit.Test


import org.junit.Assert.*

class DatabaseTest
{

    @Test
    fun checkUserSignedInTest()
    {
        var testUser = User("Mustermann", "Mustermann", "mustermann@tugraz.at", "1234")
        val testDataVar = UserAutheniticator().createUserAccount(testUser)
        assertTrue(UserAutheniticator().isUserSignedIn())
    }

    @Test
    fun getUserTest()
    {
        var testUser = User("Mustermann", "Mustermann", "mustermann@tugraz.at", "1234")
        val testDataVar = UserAutheniticator().createUserAccount(testUser)

        var currentUser = UserAutheniticator().getCurrentUser()

        if (currentUser != null) {
            assertEquals(currentUser.email, "mustermann@tugraz.at")
        }
        else
        {
            assert(false)
        }
    }

}