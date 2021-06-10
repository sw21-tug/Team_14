package com.example.tutorly

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NewTutorActivityTest : TestCase(){
    @get:Rule
    var activityNewTutor: ActivityTestRule<NewTutor> = ActivityTestRule<NewTutor>(
        NewTutor::class.java,
        true,
        false)

    var activityMain: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(
        MainActivity::class.java,
        true,
        false)

    var ruleSwitchToNewTutor =
        ActivityTestRule(NewTutor::class.java, true, false)

    @Before
    fun initIntent() {
        Intents.init()
    }

    @After
    fun releaseIntent() {
        Intents.release()
    }

    @Test
    fun uiTest() {
        activityNewTutor.launchActivity(Intent())
        Espresso.onView(ViewMatchers.withId(R.id.newTutorRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btnApply))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.phoneNum))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_change_lang_new_tutor))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.townAutocomplete_fragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun toNewTutorActivity()
    {
        activityMain.launchActivity(Intent())
        Espresso.onView(ViewMatchers.withId(R.id.btn_new_tutor))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_new_tutor)).perform(ViewActions.click())

        // Check that only once run
        ruleSwitchToNewTutor.launchActivity(Intent())
        Intents.intended(IntentMatchers.hasComponent(MainActivity::class.java.name))
    }

}