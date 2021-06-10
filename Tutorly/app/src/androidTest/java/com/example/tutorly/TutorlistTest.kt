package com.example.android.testing.espresso.BasicSample

import android.app.Activity
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.times
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.tutorly.*
import com.example.tutorly.database.LvlOfKnowledge
import com.example.tutorly.database.Tutor
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
    var activityTutorProfile: ActivityTestRule<TutorProfile> = ActivityTestRule<TutorProfile>(
            TutorProfile::class.java,
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
    fun mainActivityDisplayed()
    {
        activityMain.launchActivity(Intent())
        // check if buttons visible
        onView(withId(R.id.btnFilter))
            .check(matches(isDisplayed()))
        onView(withId(R.id.btnTownSelection))
            .check(matches(isDisplayed()))
        onView(withId(R.id.btn_new_tutor))
            .check(matches(isDisplayed()))
        onView(withId(R.id.btn_change_lang_main_activity))
            .check(matches(isDisplayed()))
    }

    @Test
    fun tutorbuttonclick() {

        activityMain.launchActivity(Intent())

        // Position 4 tests scrolling too
        onView(withId(R.id.tutorsRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerViewAdapter.ViewHolder>(0, click()))
        val tempTutor = Tutor("testId", "testName", "testLastname", "testEmail", hashMapOf(
            "German" to LvlOfKnowledge.University, "Physics" to LvlOfKnowledge.School), "12345", "Graz")
        activityTutorProfile.launchActivity(Intent().putExtra("Tutor", tempTutor))
        intended(hasComponent(TutorProfile::class.java.name), times(2))
    }

    @Test
    fun tutorListShown() {

        activityMain.launchActivity(Intent())
        onView(withId(R.id.tutorsRecyclerView)).check(matches(isDisplayed()))

    }




}