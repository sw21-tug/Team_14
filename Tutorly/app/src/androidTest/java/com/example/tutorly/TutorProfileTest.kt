package com.example.tutorly

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TutorProfileTest : TestCase() {

    // change the shown activity to the tutorprofile activity
    @get:Rule
    var activityMain: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(
        MainActivity::class.java,
        true,
        false)

    @Before
    fun initIntent() {
        Intents.init()
    }

    @After
    fun releaseIntent() {
        Intents.release()
    }

    @Test
    fun filterButton() {
        //change activity
        val intent = Intent()
        activityMain.launchActivity(intent)

        //check if button visible
        Espresso.onView(ViewMatchers.withId(R.id.btnFilter))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // perform click
        Espresso.onView(ViewMatchers.withId(R.id.btnFilter)).perform(ViewActions.click())

        //check if activity changed
        Intents.intended(IntentMatchers.hasComponent(FilterActivity::class.java.name))
    }


}