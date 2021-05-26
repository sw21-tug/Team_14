package com.example.android.testing.espresso.BasicSample

import android.app.Activity
import android.content.Intent
import android.icu.util.TimeUnit
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
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
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random


@RunWith(AndroidJUnit4::class)
@LargeTest
class TownSelectionTest {

    private lateinit var validCity: String


    @get:Rule
    var townSelection: ActivityScenarioRule<TownSelection>
            = ActivityScenarioRule(TownSelection::class.java)
    var ruleFromMainTownSelection =
            ActivityTestRule(MainActivity::class.java, true, false)

    @Before
    fun init() {
        // init Intents
        Intents.init()
        // Specify a valid string.
        validCity = "Graz"
    }

    @Test
    /*fun getToTownSelection(){
        onView(withId(R.id.btnTownSelection)).perform(click())
        ruleFromMainTownSelection.launchActivity(Intent())
        intended(hasComponent(TownSelection::class.java.name), times(2))
    }*/

    fun checkIfPopUpAppears()
    {
        onView(withId(R.id.autocomplete_fragment))
                .perform(click())
        java.util.concurrent.TimeUnit.SECONDS.sleep(2)
    }

    /*@Test
    fun typeCity(){ //some problem with the test
        // Type text
        onView(withId(R.id.autocomplete_fragment))
                .perform(click())
        java.util.concurrent.TimeUnit.SECONDS.sleep(2)

        onView(withId(R.id.autocomplete_fragment))
                .perform(click(), typeText(validCity), closeSoftKeyboard())

        // Check that the text was changed.
        onView(withId(R.id.autocomplete_fragment))
                .check(matches(withText(validCity)))

    }*/
    /*@Test
    fun cityTest()
    {
        onPlaceSelected()
    }*/



}