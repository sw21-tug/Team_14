package com.example.android.testing.espresso.BasicSample

import android.app.Activity
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.times
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.tutorly.LoginActivity
import com.example.tutorly.MainActivity
import com.example.tutorly.R
import com.example.tutorly.TutorListActivity
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random


@RunWith(AndroidJUnit4::class)
@LargeTest
class TutorlistTest {


    @get:Rule
    var activityMain: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(
            MainActivity::class.java,
            true,
            false)

    var activityTutorList: ActivityTestRule<TutorListActivity> = ActivityTestRule<TutorListActivity>(
            TutorListActivity::class.java,
            true,
            false)



    @Before
    fun init() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun tutorlistbutton() {

        activityMain.launchActivity(Intent())
        onView(withId(R.id.btnTutorlist)).perform(click())

        activityTutorList.launchActivity(Intent())
        intended(hasComponent(TutorListActivity::class.java.name), times(2))

    }

    @Test
    fun tutorbutton() {

        // add test after big merge

    }



}