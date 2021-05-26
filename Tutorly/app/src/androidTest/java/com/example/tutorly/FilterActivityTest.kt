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

    //Test swtich to filter activity
    @Test
    fun filterButton() {
        //change activity
        val intent = Intent()
        activityMain.launchActivity(intent)

        //check if button visible
        onView(withId(R.id.btnFilter)).check(matches(isDisplayed()))

        // perform click
        onView(withId(R.id.btnFilter)).perform(click())

        //check if activity changed
        intended(hasComponent(FilterActivity::class.java.name))
    }

    @get:Rule
    var activityFilter: ActivityTestRule<FilterActivity> = ActivityTestRule<FilterActivity>(
            FilterActivity::class.java,
            true,
            false)


    // Test the recyclerview and its elements
    @Test
    fun recyclerViewSelectUnselect() {

        val intent = Intent()
        activityFilter.launchActivity(intent)

        // check if the recycler view is visible
        onView(withId(R.id.filterRecyclerView)).check(matches(isDisplayed()))

        // test select subject
        onView(withId(R.id.filterRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewAdapter.ViewHolder>(4, click()))

        assertTrue(activityFilter.activity.subjectAdapter.getSubjects()[4].isSelected)

        //test unselect subject
        onView(withId(R.id.filterRecyclerView)).perform(actionOnItemAtPosition<RecyclerViewAdapter.ViewHolder>(4, click()))

        assertFalse(activityFilter.activity.subjectAdapter.getSubjects()[4].isSelected)

    }

}