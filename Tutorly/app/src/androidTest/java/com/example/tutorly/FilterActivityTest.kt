package com.example.tutorly

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import java.util.logging.Filter


@RunWith(AndroidJUnit4::class)
@SmallTest
class FilterActivityTest : TestCase() {

    @get:Rule
    var tutorProfileActivity : ActivityTestRule<TutorProfile> = ActivityTestRule<TutorProfile>(
            TutorProfile::class.java,
            true,
            false)

    @Before
    fun initIntent() {
        Intents.init()

        // change activity
        val intent = Intent()
        tutorProfileActivity.launchActivity(intent)
    }

    @After
    fun releaseIntent() {
        Intents.release()
    }

    @Test
    fun checkTitlesShown() {

        // check if titles visible
        onView(withId(R.id.txtTutorInfo)).check(matches(isDisplayed()))
        onView(withId(R.id.txtSubject)).check(matches(isDisplayed()))
    }

    @Test
    fun checkTitlesText() {

        // check if the text is right
        onView(withId(R.id.txtTutorInfo)).check(matches(withText("Tutor Information")))
        onView(withId(R.id.txtSubject)).check(matches(withText("Subjects")))

    }

}